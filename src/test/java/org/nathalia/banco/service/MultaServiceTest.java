package org.nathalia.banco.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.nathalia.banco.dto.ContaResponseDTO;
import org.nathalia.banco.entity.MultaEntity;
import org.nathalia.banco.entity.RegraMultaType;
import org.nathalia.banco.repository.MultaRepository;

public class MultaServiceTest {
	
	@InjectMocks
	private MultaService multaService;
	
	@Mock
	private MultaRepository repository;

	private ContaResponseDTO conta;
	
	@Before
	public void inicializa() {
		multaService = new MultaService(repository);
		
		conta = new ContaResponseDTO();
		conta.setNome("Conta Teste");
		conta.setValorOriginal(1500.00);
		
		Calendar dtVencimento = Calendar.getInstance();
		dtVencimento.set(2018, 3, 29);
		conta.setDataVencimento(dtVencimento);
	}
	
	@Test
	public void naoDeveAplicarMultaQuandoContaPagaAntesDoVencimento() {
		//given
		Calendar dtPagamento = Calendar.getInstance();
		dtPagamento.set(2018, 3, 28);
		this.conta.setDataPagamento(dtPagamento);
		//when
		MultaEntity multa = multaService.verificaAplicacaoDeMulta(conta);
		//then
		assertNull(multa);
		
	}
	
	@Test
	public void deveAplicarMultaQuandoContaPagaAposVencimento() {
		//given
		Calendar dtPagamento = Calendar.getInstance();
		dtPagamento.set(2018, 3, 30);
		this.conta.setDataPagamento(dtPagamento);
		
		//when
		MultaEntity multa = multaService.verificaAplicacaoDeMulta(conta);
		
		//then
		assertNotNull(multa);
		assertEquals(31.5, multa.getValor().doubleValue(), 0.1);
		assertEquals(RegraMultaType.REGRA_UM.getId(), multa.getRegraId());
	}
	
	@Test
	public void deveAplicarRegraUmQuandoDiasAtrasoMenorQueQuatro() {
		//given
		Calendar dtPagamento = Calendar.getInstance();
		dtPagamento.set(2018, 4, 2);
		this.conta.setDataPagamento(dtPagamento);
		
		//when
		MultaEntity multa = multaService.verificaAplicacaoDeMulta(conta);
		
		//then
		assertEquals(RegraMultaType.REGRA_UM.getId(), multa.getRegraId());
	}
	
	@Test
	public void deveAplicarRegraDoisQuandoDiasAtrasoMaiorQueTresEMenorQueSeis() {
		//given
		Calendar dtPagamento = Calendar.getInstance();
		dtPagamento.set(2018, 4, 3);
		this.conta.setDataPagamento(dtPagamento);
		
		//when
		MultaEntity multa = multaService.verificaAplicacaoDeMulta(conta);
		
		//then
		assertEquals(RegraMultaType.REGRA_DOIS.getId(), multa.getRegraId());
	}
	
	@Test
	public void deveAplicarRegraTresQuandoDiasAtrasoMaiorQueCinco() {
		//given
		Calendar dtPagamento = Calendar.getInstance();
		dtPagamento.set(2018, 4, 20);
		this.conta.setDataPagamento(dtPagamento);
		
		//when
		MultaEntity multa = multaService.verificaAplicacaoDeMulta(conta);
		
		//then
		assertEquals(RegraMultaType.REGRA_TRES.getId(), multa.getRegraId());
	}

}
