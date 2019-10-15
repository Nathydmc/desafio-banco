package org.nathalia.banco.entity;

public enum RegraMultaType {
	
	REGRA_UM(1L, 0.02, 0.001), REGRA_DOIS(2L, 0.03, 0.002), REGRA_TRES(3L, 0.05, 0.003);
	
	private Long id;
	
	private Double taxaMulta;
	
	private Double jurosDia;
	
	RegraMultaType(Long id, Double taxaMulta, Double jurosDia) {
		this.id = id;
		this.taxaMulta = taxaMulta;
		this.jurosDia = jurosDia;
	}

	public Long getId() {
		return id;
	}

	public Double getTaxaMulta() {
		return taxaMulta;
	}

	public Double getJurosDia() {
		return jurosDia;
	}
	
	public RegraMultaType getRegraById(Integer id) {
		if (id == 1) {
			return REGRA_UM; 
		} else if (id == 2) {
			return REGRA_DOIS;
		} else {
			return REGRA_TRES;			
		}
	}
	

}
