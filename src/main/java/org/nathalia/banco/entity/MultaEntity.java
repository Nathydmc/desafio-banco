package org.nathalia.banco.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "MULTA")
public class MultaEntity implements Serializable{
	
	private static final long serialVersionUID = 6406302775069470333L;
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	private Long contaId;
	
	private Double valor;
	
	private Long regraId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}

	public Long getRegraId() {
		return regraId;
	}

	public void setRegraId(Long regraId) {
		this.regraId = regraId;
	}	

	
}
