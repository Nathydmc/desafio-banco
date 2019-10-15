package org.nathalia.banco.dto;

import java.util.Calendar;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContaRequestDTO {

	@NotBlank(message="O nome é obrigatório")
	private String nome;

	@NotNull(message="O valor é obrigatório.")
	private Double valorOriginal;

	@NotNull(message="A data de vencimento é obrigatória.")
	private Calendar dataVencimento;

	@NotNull(message="A data de pagamento é obrigatória.")
	private Calendar dataPagamento;

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

	
}
