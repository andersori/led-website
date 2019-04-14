package com.github.andersori.led.bean;


import com.github.andersori.led.entity.Turma;

public class TurmaBean implements Bean<Turma>{
	
	private Long id;
    private String nome;
    private String codDisciplina;
    private SemestreBean semestre;
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
	
	public SemestreBean getSemestre() {
		return semestre;
	}
	
	public void setSemestre(SemestreBean semestre) {
		this.semestre = semestre;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Override
	public void toBean(Turma entity) {
		if(entity != null) {
			setId(entity.getId());
			setCodDisciplina(entity.getCodDisciplina());
			
			SemestreBean semes = new SemestreBean();
			semes.toBean(entity.getSemestre());
			
			setSemestre(semes);
			setCurso(entity.getCurso());
			setNome(entity.getNome());
			
		} 
		/*else {
			throw new NullPointerException("Entidade Turma nula na converção para bean.");
		}*/
	}

	@Override
	public Turma toEntity() {
		Turma entity = new Turma();
		entity.setId(getId());
		entity.setCodDisciplina(getCodDisciplina());
		entity.setSemestre(getSemestre().toEntity());
		entity.setCurso(getCurso());
		entity.setNome(getNome());
		return entity;
	}
	
}
