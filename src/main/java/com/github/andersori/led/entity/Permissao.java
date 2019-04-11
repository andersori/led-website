package com.github.andersori.led.entity;

public enum Permissao {
	
	ADM(1),
	EQUIPE(2);
	
	private int id;
	
	private Permissao(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
