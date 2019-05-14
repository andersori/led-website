package com.github.andersori.led.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Equipe")
@Table(name = "equipe")
@NamedQueries(value = {
    @NamedQuery(name = "equipe_get_by_user", query = "from Equipe e where e.usuario = :usuario"),
    @NamedQuery(name = "equipe_get_by_maratona", query = "from Equipe e where e.maratona = :maratona"),
    @NamedQuery(name = "equipe_get_by_casa", query = "from Equipe e where e.casa = :casa"),
    @NamedQuery(name = "equipe_get_by_maratona_casa", query = "from Equipe e where e.maratona = :maratona and e.casa = :casa"),
})
public class Equipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipe", columnDefinition = "serial")
    private Long id;

    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_user")
    private Usuario usuario;

    @ManyToOne(targetEntity = Turma.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_turma", nullable = false)
    private Turma turma;
    
    @Column(name = "pontos", nullable = false)
    private Integer pontos;

    @Column(name = "casa", nullable = false)
    private Casa casa;
    
    @ManyToOne(targetEntity = Maratona.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_maratona", nullable = true)
    private Maratona maratona;
    
    public Equipe() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

	public Maratona getMaratona() {
		return maratona;
	}

	public void setMaratona(Maratona maratona) {
		this.maratona = maratona;
	}
    
}
