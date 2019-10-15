package org.nathalia.banco.dto;

import java.util.Calendar;

public class ContaResponseDTO {
	
	private String nome;
	
	private Double valorOriginal;
	
	private Double valorCorrigido;
	
	private Calendar dataVencimento;
	
	private Calendar dataPagamento;
	
	private Integer qtdDiasAtraso;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(Double valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public Double getValorCorrigido() {
		return valorCorrigido;
	}

	public void setValorCorrigido(Double valorCorrigido) {
		this.valorCorrigido = valorCorrigido;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Integer getQtdDiasAtraso() {
		return qtdDiasAtraso;
	}

	public void setQtdDiasAtraso(Integer qtdDiasAtraso) {
		this.qtdDiasAtraso = qtdDiasAtraso;
	}

	
}
