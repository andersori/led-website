package com.github.andersori.led.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Equipe")
@Table(name = "equipe")
public class Equipe {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipe")
    private Long id;

    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_user")
    private Usuario usuario;

    @OneToMany(targetEntity = Membro.class, fetch = FetchType.EAGER)
    @JoinTable(
        name = "membro_equipe",
        joinColumns = {@JoinColumn(name = "team", referencedColumnName = "id_equipe")},
        inverseJoinColumns = {@JoinColumn(name = "membro", referencedColumnName = "id_membro")}
    )
    private List<Membro> membros;

    @ManyToOne(targetEntity = Turma.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_turma", nullable = false)
    private Turma turma;

    @Column(name = "casa", nullable = false)
    private Casa casa;
    
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

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}
    
}
