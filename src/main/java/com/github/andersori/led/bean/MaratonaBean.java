package com.github.andersori.led.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Maratona;

public class MaratonaBean implements Bean<Maratona>{
	
	private Long id;
	private SemestreBean semestre;
	private List<EquipeBean> equipes;
	private LocalDate data;
	private EquipeBean vencedor;
	
	public MaratonaBean() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SemestreBean getSemestre() {
		return semestre;
	}

	public void setSemestre(SemestreBean semestre) {
		this.semestre = semestre;
	}

	public List<EquipeBean> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<EquipeBean> equipes) {
		this.equipes = equipes;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public EquipeBean getVencedor() {
		return vencedor;
	}

	public void setVencedor(EquipeBean vencedor) {
		this.vencedor = vencedor;
	}

	@Override
	public void toBean(Maratona entity) {
		if(entity != null) {
			setId(entity.getId());
			setData(entity.getData());
			
			List<EquipeBean> list = new ArrayList<EquipeBean>();
			for(Equipe e : entity.getEquipes()) {
				EquipeBean equipeBean = new EquipeBean();
				equipeBean.toBean(e);
				list.add(equipeBean);
			}
			setEquipes(list);
			
			SemestreBean semestreBean = new SemestreBean();
			semestreBean.toBean(entity.getSemestre());
			setSemestre(semestreBean);
			
			EquipeBean equipeBean = new EquipeBean();
			equipeBean.toBean(entity.getVencedor());
			setVencedor(equipeBean);
		} else {
			throw new NullPointerException("Entidade Maratona nula na converção para bean.");
		}
	}

	@Override
	public Maratona toEntity() {
		Maratona maratona = new Maratona();
		maratona.setId(getId());
		maratona.setData(getData());
		maratona.setSemestre(getSemestre().toEntity());
		maratona.setVencedor(getVencedor().toEntity());
		
		List<Equipe> list = new ArrayList<Equipe>();
		for(EquipeBean e : getEquipes()) {
			list.add(e.toEntity());
		}
		maratona.setEquipes(list);
		return maratona;
	}
	
	
}
