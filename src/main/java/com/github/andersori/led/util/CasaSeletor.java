package com.github.andersori.led.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.andersori.led.dao.EquipeDAO;
import com.github.andersori.led.dao.hibernate.EquipeHib;
import com.github.andersori.led.entity.Casa;
import com.github.andersori.led.entity.Equipe;

public class CasaSeletor {
	
	private static final EquipeDAO daoEquipe = new EquipeHib();
	
	public CasaSeletor() {
		
	}
	
	public static synchronized Equipe getEquipe(Long id) {
		try {
			Equipe equipe = daoEquipe.get(id);
			
			int qtdLufalufa = 0;
			int qtdCorvinal = 0;
			int qtdGrifinoria = 0;
			int qtdSonserina = 0;
			
			List<Equipe> equipesMaratona = daoEquipe.listByMaratona(equipe.getMaratona());
			
			for(Equipe e : equipesMaratona) {
				if(e.getCasa() == Casa.CORVINAL) {
					qtdCorvinal++;
				} else if(e.getCasa() == Casa.GRIFINORIA) {
					qtdGrifinoria++;
				} else if(e.getCasa() == Casa.LUFALUFA) {
					qtdLufalufa++;
				} else if(e.getCasa() == Casa.SONSERINA) {
					qtdSonserina++;
				}
			}
			
			int maxPorCasa = (int) Math.floor(equipesMaratona.size() / 4);
			
			Casa casaEscolhida = Casa.INDEFINIDO;
			
			List<Casa> casas = new ArrayList<Casa>();
			if(qtdCorvinal < maxPorCasa+1) {
				casas.add(Casa.CORVINAL);
			}
			if(qtdGrifinoria < maxPorCasa+1) {
				casas.add(Casa.GRIFINORIA);
			}
			if(qtdLufalufa < maxPorCasa+1) {
				casas.add(Casa.LUFALUFA);
			}
			if(qtdSonserina < maxPorCasa+1) {
				casas.add(Casa.SONSERINA);
			}
			Random rand = new Random();
			casaEscolhida = casas.get(rand.nextInt(casas.size()));
			
			
			equipe.setCasa(casaEscolhida);
			daoEquipe.update(equipe);
			return equipe;
		} catch(Exception e) {
			System.err.println("Não foi possivel definir uma casa.");
			e.printStackTrace();
		}
		
		return null;
	}
}
