package com.github.andersori.led.bean;

import java.util.ArrayList;
import java.util.List;

import com.github.andersori.led.entity.Casa;
import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Membro;

public class EquipeBean {

    private Long id;
    private UsuarioBean usuario;
    private List<MembroBean> membros;
    private TurmaBean turma;
    private Casa casa;
    
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

	public List<MembroBean> getMembros() {
		return membros;
	}

	public void setMembros(List<MembroBean> membros) {
		this.membros = membros;
	}

	public TurmaBean getTurma() {
		return turma;
	}

	public void setTurma(TurmaBean turma) {
		this.turma = turma;
	}

	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

	public static EquipeBean toBean(Equipe entity) {
		if(entity != null) {
			EquipeBean bean = new EquipeBean();
			bean.setId(entity.getId());
			bean.setCasa(entity.getCasa());
			
			bean.setUsuario(UsuarioBean.toBean(entity.getUsuario()));
			bean.setTurma(TurmaBean.toBean(entity.getTurma()));
			List<MembroBean> lis = new ArrayList<>();
			for(Membro m : entity.getMembros()) {
				lis.add(MembroBean.toBean(m));
			}
			bean.setMembros(lis);
			
			return bean;
		}
		return null;
	}

	public static Equipe toEntity(EquipeBean bean) {
		if(bean != null) {
			Equipe entity = new Equipe();
			entity.setId(bean.getId());
			entity.setCasa(entity.getCasa());
			
			entity.setUsuario(UsuarioBean.toEntity(bean.getUsuario()));
			entity.setTurma(TurmaBean.toEntity(bean.getTurma()));
			List<Membro> lis = new ArrayList<>();
			for(MembroBean m : bean.getMembros()) {
				lis.add(MembroBean.toEntity(m));
			}
			entity.setMembros(lis);
			
			return entity;
		}
		return null;
	}
    	
}
