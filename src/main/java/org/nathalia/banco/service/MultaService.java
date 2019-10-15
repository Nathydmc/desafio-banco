package org.nathalia.banco.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.TimeZone;

import org.hibernate.internal.util.compare.CalendarComparator;
import org.nathalia.banco.dto.ContaResponseDTO;
import org.nathalia.banco.entity.ContaEntity;
import org.nathalia.banco.entity.MultaEntity;
import org.nathalia.banco.entity.RegraMultaType;
import org.nathalia.banco.repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultaService {
	
	private MultaRepository multaRepository;

	@Autowired
	public MultaService(MultaRepository multaRepository) {
		super();
		this.multaRepository = multaRepository;
	}

	public MultaEntity verificaAplicacaoDeMulta(ContaResponseDTO contaDto) {
		if (isPagamentoAtrasado(contaDto)) {
			return calculaMulta(contaDto);
		}
		return null;
	}

	private boolean isPagamentoAtrasado(ContaResponseDTO contaDto) {
		return CalendarComparator.INSTANCE.compare(contaDto.getDataPagamento(), contaDto.getDataVencimento())==1;
	}

	private MultaEntity calculaMulta(ContaResponseDTO contaDto) {
		MultaEntity multa = new MultaEntity();
		
		Integer diasAtraso = calculaDiasAtraso(contaDto);
		RegraMultaType regra = estabeleceRegra(diasAtraso);
		multa.setRegraId(regra.getId());
		
		Double valorOriginal = contaDto.getValorOriginal();
		Double valorMulta = valorOriginal * (regra.getTaxaMulta() + (regra.getJurosDia() * diasAtraso));
		multa.setValor(valorMulta);
		
		contaDto.setValorCorrigido(valorOriginal + valorMulta);
		contaDto.setQtdDiasAtraso(diasAtraso);
		
		return multa;
	}

	private Integer calculaDiasAtraso(ContaResponseDTO contaDto) {
		TimeZone tz = contaDto.getDataPagamento().getTimeZone();
		ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
		
		LocalDate dtPagamento = LocalDateTime.ofInstant(contaDto.getDataPagamento().toInstant(), zid).toLocalDate();
		LocalDate dtVencimento = LocalDateTime.ofInstant(contaDto.getDataVencimento().toInstant(), zid).toLocalDate();		
		
		return Period.between(dtVencimento, dtPagamento).getDays();
	}
	
	private RegraMultaType estabeleceRegra(Integer diasAtraso) {
		if (diasAtraso <= 3) {
			return RegraMultaType.REGRA_UM;
		} else if (diasAtraso <= 5) {
			return RegraMultaType.REGRA_DOIS;
		}
		return RegraMultaType.REGRA_TRES;
	}

	public void incluirMulta(MultaEntity multa, ContaEntity conta) {
		if (multa != null) {
			multa.setContaId(conta.getId());
			multaRepository.save(multa);			
		}
	}
	
	

}
