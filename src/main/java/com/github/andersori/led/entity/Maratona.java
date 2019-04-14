package com.github.andersori.led.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Maratona")
@Table(name = "maratona")
public class Maratona {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_maratona")
    private Long id;
	
	@ManyToOne(targetEntity = Semestre.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_semestre_maratona", nullable = false)
	private Semestre semestre;
	
	@OneToMany(targetEntity = Equipe.class, fetch = FetchType.EAGER)
    @JoinTable(
        name = "equipe_maratona",
        joinColumns = {@JoinColumn(name = "maratona", referencedColumnName = "id_maratona")},
        inverseJoinColumns = {@JoinColumn(name = "equipe", referencedColumnName = "id_equipe")}
    )
	private List<Equipe> equipes;
	
	@Column(name = "data", nullable = false)
	private LocalDate data;
	
	@ManyToOne(targetEntity = Equipe.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_semestre", nullable = false)
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

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
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
