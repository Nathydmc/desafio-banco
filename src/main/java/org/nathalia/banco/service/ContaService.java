package org.nathalia.banco.service;

import java.util.List;
import java.util.stream.Collectors;

import org.nathalia.banco.converter.ContaConverter;
import org.nathalia.banco.dto.ContaRequestDTO;
import org.nathalia.banco.dto.ContaResponseDTO;
import org.nathalia.banco.entity.ContaEntity;
import org.nathalia.banco.entity.MultaEntity;
import org.nathalia.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

	private ContaConverter converter;
	private ContaRepository contaRepository;
	private MultaService multaService;
	
	@Autowired
	public ContaService(ContaConverter converter, ContaRepository contaRepository, MultaService multaService) {
		super();
		this.converter = converter;
		this.contaRepository = contaRepository;
		this.multaService = multaService;
	}

	public ContaResponseDTO incluirConta(ContaRequestDTO requestDto) {
		ContaResponseDTO responseDto = converter.toResponse(requestDto);
		MultaEntity multa = multaService.verificaAplicacaoDeMulta(responseDto);
		ContaEntity conta = contaRepository.save(converter.toEntity(responseDto));
		multaService.incluirMulta(multa, conta);
		return responseDto;
	}

	public List<ContaResponseDTO> listarContas() {
		List<ContaEntity> contas = contaRepository.findAll();
		return contas.stream().map(conta -> converter.toDTO(conta)).collect(Collectors.toList());
	}

}
