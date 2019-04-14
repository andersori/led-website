package com.github.andersori.led.bean;

import com.github.andersori.led.entity.Aluno;

public class AlunoBean implements Bean<Aluno>{
	
	private Long id;
    private String nome;
    private String matricula;
    private TurmaBean turma;
    private EquipeBean equipe;
    
    public AlunoBean() {
    	
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

	public TurmaBean getTurma() {
		return turma;
	}

	public void setTurma(TurmaBean turma) {
		this.turma = turma;
	}

	public EquipeBean getEquipe() {
		return equipe;
	}

	public void setEquipe(EquipeBean equipe) {
		this.equipe = equipe;
	}

	@Override
	public void toBean(Aluno entity) {
		if(entity != null) {
			setId(entity.getId());
			setMatricula(entity.getMatricula());
			setNome(entity.getNome());
			
			EquipeBean equipeBean = new EquipeBean();
			equipeBean.toBean(entity.getEquipe());
			setEquipe(equipeBean);
			
			TurmaBean turmaBean = new TurmaBean();
			turmaBean.toBean(entity.getTurma());
			setTurma(turmaBean);
		} else {
			throw new NullPointerException("Entidade Aluno nula na converção para bean.");
		}
	}

	@Override
	public Aluno toEntity() {
		Aluno entity = new Aluno();
		entity.setId(getId());
		entity.setEquipe(getEquipe().toEntity());
		entity.setMatricula(getMatricula());
		entity.setNome(getNome());
		entity.setTurma(getTurma().toEntity());
		return entity;
	}
}
