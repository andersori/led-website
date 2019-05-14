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

@Entity(name = "Turma")
@Table(name = "turma")
@NamedQueries(value = {
	    @NamedQuery(name = "get_turma_by_semestre", query = "from Turma t where t.semestre = :semestre")
})
public class Turma {
	
	@Id
	@SequenceGenerator(name="turmaIdSeq", sequenceName="turma_id_turma_seq", allocationSize=1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turmaIdSeq")
    @Column(name = "id_turma", columnDefinition = "serial")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "codDisciplina", nullable = true)
    private String codDisciplina;
    
    @ManyToOne(targetEntity = Semestre.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_semestre_turma", nullable = false)
	private Semestre semestre;

    @Column(name = "curso", nullable = false)
    private String curso;
    
    public Turma () {
    	
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

	public String getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(String codDisciplina) {
		this.codDisciplina = codDisciplina;
	}
	
	public Semestre getSemestre() {
		return semestre;
	}
	
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
    
}
