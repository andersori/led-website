package com.github.andersori.led.bean;

import com.github.andersori.led.entity.Casa;
import com.github.andersori.led.entity.Equipe;

public class EquipeBean implements Bean<Equipe>{

    private Long id;
    private UsuarioBean usuario;
    private TurmaBean turma;
    private Integer pontos;
    private Casa casa;
    private MaratonaBean maratona;
    
    public EquipeBean() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public TurmaBean getTurma() {
		return turma;
	}

	public void setTurma(TurmaBean turma) {
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

	public MaratonaBean getMaratona() {
		return maratona;
	}

	public void setMaratona(MaratonaBean maratona) {
		this.maratona = maratona;
	}

	@Override
	public void toBean(Equipe entity) {
		if(entity != null) {
			setId(entity.getId());
			setCasa(entity.getCasa());
			setPontos(entity.getPontos());
			
			UsuarioBean usuarioBean = new UsuarioBean();
			usuarioBean.toBean(entity.getUsuario());
			setUsuario(usuarioBean);
			
			TurmaBean turmaBean = new TurmaBean();
			turmaBean.toBean(entity.getTurma());
			setTurma(turmaBean);
			
			MaratonaBean maratonaBean = new MaratonaBean();
			maratonaBean.toBean(entity.getMaratona());
			setMaratona(maratonaBean);
			
				
		} 
		/*else {
			throw new NullPointerException("Entidade Equipe nula na converção para bean.");
		}*/
	}

	@Override
	public Equipe toEntity() {
		Equipe entity = new Equipe();
		entity.setCasa(getCasa());
		entity.setId(getId());
		entity.setPontos(getPontos());
		entity.setTurma(getTurma().toEntity());
		entity.setUsuario(getUsuario().toEntity());
		entity.setMaratona(getMaratona().toEntity());
		
		return entity;
	}
    	
}
