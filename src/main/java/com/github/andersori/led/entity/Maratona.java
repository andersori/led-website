package com.github.andersori.led.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Maratona")
@Table(name = "maratona")
public class Maratona {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_maratona", columnDefinition = "serial")
    private Long id;
	
	@ManyToOne(targetEntity = Semestre.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_semestre", nullable = false)
	private Semestre semestre;
	
	@Column(name = "data", nullable = false)
	private LocalDate data;
	
	@ManyToOne(targetEntity = Equipe.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vencedor", nullable = true)
	private Equipe vencedor;
	
	public Maratona() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Equipe getVencedor() {
		return vencedor;
	}

	public void setVencedor(Equipe vencedor) {
		this.vencedor = vencedor;
	}
	
}
