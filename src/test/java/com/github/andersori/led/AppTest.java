package com.github.andersori.led;

import org.mindrot.jbcrypt.BCrypt;

import com.github.andersori.led.bean.UsuarioBean;
import com.github.andersori.led.dao.EquipeDAO;
import com.github.andersori.led.dao.AlunoDAO;
import com.github.andersori.led.dao.TurmaDAO;
import com.github.andersori.led.dao.UsuarioDAO;
import com.github.andersori.led.dao.hibernate.EquipeHib;
import com.github.andersori.led.dao.hibernate.AlunoHib;
import com.github.andersori.led.dao.hibernate.TurmaHib;
import com.github.andersori.led.dao.hibernate.UsuarioHib;
import com.github.andersori.led.entity.Casa;
import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Aluno;
import com.github.andersori.led.entity.Permissao;
import com.github.andersori.led.entity.Turma;
import com.github.andersori.led.entity.Usuario;

public class AppTest{
    
	public static void main(String[] args) {
		usuario();
		//aluno();
		//turma();
		//equipe();
	}
	
	public static void usuario() {
		UsuarioBean u = new UsuarioBean();
		u.setNome("Anderson Soriano");
		u.setUsername("andersori");
		u.setSenha(BCrypt.hashpw("1234", BCrypt.gensalt()));
		u.setPermissao(Permissao.ADM);
		
		Usuario uData = UsuarioBean.toEntity(u);
		
		UsuarioDAO dao = new UsuarioHib();
		dao.add(uData);
		
		System.out.println("--------------Pegando o usuario--------------");
		System.out.println(dao.get("user").getNome());
	}
	
	public static void aluno() {
		TurmaDAO daoT = new TurmaHib();
		AlunoDAO daoM = new AlunoHib();
		
		Turma t = new Turma();
		t.setCodDisciplina("3212d");
		t.setNome("Estrutura de dados");
		t.setCurso("Engenharia de software");
		
		daoT.add(t);
		
		Aluno m = new Aluno();
		m.setNome("Teste silva");
		m.setMatricula("423122");
		m.setTurma(t);
		
		daoM.add(m);
		
		for(Aluno mm : daoM.list()) {
			System.out.println(mm.getNome()+"::"+mm.getTurma().getNome()+"::"+mm.getTurma().getCurso());
		}
	}
	
	public static void turma() {
		TurmaDAO daoT = new TurmaHib();
		
		Turma t1 = new Turma();
		t1.setCodDisciplina("RUSS0012");
		t1.setCurso("Ciência da Computação");
		t1.setNome("Estrutura de dados");
		
		Turma t2 = new Turma();
		t2.setCodDisciplina("RUSS0012");
		t2.setCurso("Engenharia de Software");
		t2.setNome("Estrutura de dados");
		
		daoT.add(t1);
		daoT.add(t2);
		
		for(Turma tt : daoT.list()) {
			System.out.println(tt.getCodDisciplina()+"::"+tt.getNome()+"::"+tt.getCurso());
		}
		
	}
	
	public static void equipe() {
		UsuarioDAO daoU = new UsuarioHib();
		EquipeDAO daoE = new EquipeHib();
		TurmaDAO daoT = new TurmaHib();
		AlunoDAO daoM = new AlunoHib();
		
		Usuario u = new Usuario();
		u.setNome("SOS");
		u.setPermissao(Permissao.EQUIPE);
		u.setSenha(BCrypt.hashpw("12345", BCrypt.gensalt()));
		u.setUsername("sos");
		daoU.add(u);
		
		Turma t = new Turma();
		t.setCodDisciplina("RUSS0121");
		t.setNome("Estrutura de dados");
		t.setCurso("Engenharia de Software");
		daoT.add(t);
		
		Equipe e = new Equipe();
		e.setCasa(Casa.INDEFINIDO);
		e.setTurma(t);
		e.setUsuario(u);
		daoE.add(e);
		
		Aluno m1 = new Aluno();
		m1.setNome("Teste silva");
		m1.setMatricula("212131");
		m1.setTurma(t);
		
		Aluno m2 = new Aluno();
		m2.setNome("Teste araujo");
		m2.setMatricula("423122");
		m2.setTurma(t);
		daoM.add(m1);
		daoM.add(m2);
		
		for(Equipe ee : daoE.list()) {
			System.out.println(ee.getUsuario().getNome()+"::"+ee.getTurma().getNome()+"::"+ee.getTurma().getCodDisciplina());
		}
		
	}


}
