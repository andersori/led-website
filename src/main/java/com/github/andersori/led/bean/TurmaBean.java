package com.github.andersori.led.bean;


import com.github.andersori.led.entity.Turma;

public class TurmaBean {
	
	private Long id;
    private String nome;
    private String codDisciplina;
    private String curso;
    
    public TurmaBean() {
    	
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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public static TurmaBean toBean(Turma entity) {
		if(entity != null) {
			TurmaBean bean = new TurmaBean();
			bean.setId(entity.getId());
			bean.setCodDisciplina(entity.getCodDisciplina());
			bean.setCurso(entity.getCurso());
			bean.setNome(entity.getNome());
			return bean;
		}
		return null;
	}

	public static Turma toEntity(TurmaBean bean) {
		if(bean != null) {
			Turma entity = new Turma();
			entity.setId(bean.getId());
			entity.setCodDisciplina(bean.getCodDisciplina());
			entity.setCurso(bean.getCurso());
			entity.setNome(bean.getNome());
			return entity;
		}
		return null;
	}
	
}
