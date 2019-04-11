package com.github.andersori.led.bean;

import com.github.andersori.led.entity.Membro;

public class MembroBean {
	
	private Long id;
    private String nome;
    private String matricula;
    private TurmaBean turma;
    private EquipeBean equipe;
    
    public MembroBean() {
    	
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

	public static MembroBean toBean(Membro entity) {
		if(entity != null) {
			MembroBean bean = new MembroBean();
			bean.setId(entity.getId());
			bean.setMatricula(entity.getMatricula());
			bean.setNome(entity.getNome());
			
			bean.setEquipe(EquipeBean.toBean(entity.getEquipe()));
			bean.setTurma(TurmaBean.toBean(entity.getTurma()));
			return bean;
		}
		return null;
	}

	public static Membro toEntity(MembroBean bean) {
		if(bean != null) {
			Membro entity = new Membro();
			entity.setId(bean.getId());
			entity.setMatricula(bean.getMatricula());
			entity.setNome(bean.getNome());
			
			entity.setEquipe(EquipeBean.toEntity(bean.getEquipe()));
			entity.setTurma(TurmaBean.toEntity(bean.getTurma()));
			return entity;
		}
		return null;
	}
}
