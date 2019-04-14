package com.github.andersori.led.bean;

import java.time.LocalDate;

import com.github.andersori.led.entity.Maratona;

public class MaratonaBean implements Bean<Maratona>{
	
	private Long id;
	private SemestreBean semestre;
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
			
			SemestreBean semestreBean = new SemestreBean();
			semestreBean.toBean(entity.getSemestre());
			setSemestre(semestreBean);
			
			EquipeBean equipeBean = new EquipeBean();
			equipeBean.toBean(entity.getVencedor());
			setVencedor(equipeBean);
		} 
		/*else {
			throw new NullPointerException("Entidade Maratona nula na converção para bean.");
		}*/
	}

	@Override
	public Maratona toEntity() {
		Maratona maratona = new Maratona();
		maratona.setId(getId());
		maratona.setData(getData());
		maratona.setSemestre(getSemestre().toEntity());
		maratona.setVencedor(getVencedor().toEntity());
		return maratona;
	}
	
	
}
