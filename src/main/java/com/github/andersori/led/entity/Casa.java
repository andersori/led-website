package com.github.andersori.led.entity;

public enum Casa {
	
	INDEFINIDO(0),
	GRIFINORIA(1),
    CORVINAL(2),
    SONSERINA(3),
    LUFALUFA(4);
	
	private int id;
	
	private Casa(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static Casa getCasa(String nome) {
		if(nome.toUpperCase().equals("GRIFINORIA")) {
			return GRIFINORIA;
		} else if(nome.toUpperCase().equals("CORVINAL")) {
			return CORVINAL;
		} else if(nome.toUpperCase().equals("SONSERINA")) {
			return SONSERINA;
		} else if(nome.toUpperCase().equals("LUFALUFA")) {
			return LUFALUFA;
		} else {
			return INDEFINIDO;
		}
	}
}
