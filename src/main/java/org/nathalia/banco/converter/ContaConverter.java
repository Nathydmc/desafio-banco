package org.nathalia.banco.converter;

import org.nathalia.banco.dto.ContaRequestDTO;
import org.nathalia.banco.dto.ContaResponseDTO;
import org.nathalia.banco.entity.ContaEntity;
import org.springframework.stereotype.Component;

@Component
public class ContaConverter {
	
	public ContaEntity toEntity(ContaRequestDTO dto) {
		ContaEntity conta = new ContaEntity();
		conta.setNome(dto.getNome());
		conta.setValorOriginal(dto.getValorOriginal());
		conta.setDataVencimento(dto.getDataVencimento());
		conta.setDataPagamento(dto.getDataPagamento());
		
		return conta;
	}
	
	public ContaEntity toEntity(ContaResponseDTO dto) {
		ContaEntity conta = new ContaEntity();
		conta.setNome(dto.getNome());
		conta.setValorOriginal(dto.getValorOriginal());
		conta.setValorTotal(dto.getValorCorrigido());
		conta.setQtdDiasAtraso(dto.getQtdDiasAtraso());
		conta.setDataVencimento(dto.getDataVencimento());
		conta.setDataPagamento(dto.getDataPagamento());
		
		return conta;
	}
	
	public ContaResponseDTO toDTO (ContaEntity entity) {
		ContaResponseDTO dto = new ContaResponseDTO();
		dto.setNome(entity.getNome());
		dto.setValorOriginal(entity.getValorOriginal());
		dto.setValorCorrigido(entity.getValorTotal());
		dto.setQtdDiasAtraso(entity.getQtdDiasAtraso());
		dto.setDataPagamento(entity.getDataPagamento());
		dto.setDataVencimento(entity.getDataVencimento());
		
		return dto;
	}

	public ContaResponseDTO toResponse(ContaRequestDTO requestDto) {
		ContaResponseDTO dto = new ContaResponseDTO();
		dto.setNome(requestDto.getNome());
		dto.setValorOriginal(requestDto.getValorOriginal());
		dto.setDataPagamento(requestDto.getDataPagamento());
		dto.setDataVencimento(requestDto.getDataVencimento());
		
		return dto;
	}

}
