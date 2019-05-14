package com.github.andersori.led.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name = "Semestre")
@Table(name = "semestre")
@NamedQueries(value = {
	    @NamedQuery(name = "semestre_ja_existe", query = "select count(s) from Semestre s where s.ano = :ano and s.numSemestre = :numSemestre")
})
public class Semestre {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_semestre", columnDefinition = "serial")
    private Long id;
	
	@Column(name = "ano", nullable = false)
	private Integer ano;
	
	@Column(name = "num_semestre", nullable = false)
	private Integer numSemestre;
	
	public Semestre() {
		
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
	
}
