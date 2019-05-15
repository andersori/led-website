package com.github.andersori.led;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.github.andersori.led.controller.Autenticado;
import com.github.andersori.led.dao.UsuarioDAO;
import com.github.andersori.led.dao.hibernate.UsuarioHib;
import com.github.andersori.led.entity.Permissao;
import com.github.andersori.led.entity.Usuario;

@SpringBootApplication
public class App extends SpringBootServletInitializer{
	
    public static void main( String[] args ){
       SpringApplication.run(App.class, args);
       
       //CRIANDO USUARIO PRA LOGAR NO SISTEMA
       Usuario adm = new Usuario();
       adm.setNome("Administrador");
       adm.setPermissao(Permissao.ADM);
       adm.setUsername("adm");
       adm.setSenha(BCrypt.hashpw("1234", BCrypt.gensalt()));
       
       UsuarioDAO daoU = new UsuarioHib();
       daoU.add(adm);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }
    
    @Bean
    public FilterRegistrationBean<Autenticado> filter() {
    	final FilterRegistrationBean<Autenticado> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new Autenticado());
        return registrationBean;
    }
}
