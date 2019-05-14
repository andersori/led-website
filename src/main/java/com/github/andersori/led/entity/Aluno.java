package com.github.andersori.led.entity;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Aluno")
@Table(name = "aluno")
@NamedQueries(value = {
	    @NamedQuery(name = "aluno_get_by_equipe", query = "from Aluno a where a.equipe = :equipe"),
	    @NamedQuery(name = "aluno_get_by_turma", query = "from Aluno a where a.turma = :turma")
})
public class Aluno {
	
	@Id
	@SequenceGenerator(name="alunoIdSeq", sequenceName="aluno_id_aluno_seq", allocationSize=1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alunoIdSeq")
    @Column(name = "id_aluno", columnDefinition = "serial")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(unique = true, name = "matricula", nullable = false)
    private String matricula;

    @ManyToOne(targetEntity = Turma.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_turma", nullable = false)
    private Turma turma;

    @ManyToOne(targetEntity = Equipe.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_equipe", nullable = true)
    private Equipe equipe;
    
    
    public Aluno() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
    
}
