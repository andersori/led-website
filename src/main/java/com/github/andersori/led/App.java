package com.github.andersori.led;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.github.andersori.led.controller.Autenticado;

@SpringBootApplication
public class App extends SpringBootServletInitializer{
	
    public static void main( String[] args ){
       SpringApplication.run(App.class, args);
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
