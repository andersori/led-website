package com.github.andersori.led.bean;

import com.github.andersori.led.entity.Semestre;

public class SemestreBean implements Bean<Semestre>{
	
    private Long id;
	private Integer ano;
	private Integer numSemestre;
	
	public SemestreBean() {
		this.id = 0L;
		this.ano = 1;
		this.numSemestre = 1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getNumSemestre() {
		return numSemestre;
	}

	public void setNumSemestre(Integer numSemestre) {
		this.numSemestre = numSemestre;
	}

	@Override
	public void toBean(Semestre entity) {
		if(entity != null) {
			setAno(entity.getAno());
			setId(entity.getId());
			setNumSemestre(entity.getNumSemestre());
		} 
		/*else {
			throw new NullPointerException("Entidade Semestre nula na converção para bean.");
		}*/
	}

	@Override
	public Semestre toEntity() {
		Semestre entity = new Semestre();
		entity.setAno(getAno());
		entity.setId(getId());
		entity.setNumSemestre(getNumSemestre());
		return entity;
	}
	
}
