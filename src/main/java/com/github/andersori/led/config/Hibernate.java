package com.github.andersori.led.config;

import java.util.HashMap;
import java.util.Map;

import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Membro;
import com.github.andersori.led.entity.Turma;
import com.github.andersori.led.entity.Usuario;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

public class Hibernate{

    private static Hibernate SINGLE_INSTANCE = null;

    private static StandardServiceRegistry registry;
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private Hibernate(){
    	
    }

    public static Hibernate getInstance(){

        if(SINGLE_INSTANCE == null){
            synchronized(Hibernate.class){
                if(SINGLE_INSTANCE == null){
                    SINGLE_INSTANCE = new Hibernate();
                }
            }
        }
        return SINGLE_INSTANCE;
    }

    private static SessionFactory buildSessionFactory(){
        try{
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
            Database db = Database.getInstance();

            Map<String, String> settings = new HashMap<String, String>();
            settings.put(Environment.DRIVER, db.getDrive());
            settings.put(Environment.URL, db.getUrl());
            settings.put(Environment.USER, db.getUser());
            settings.put(Environment.PASS, db.getPassword());
            settings.put(Environment.DIALECT, db.getDialect());
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.POOL_SIZE, "10");
            //settings.put(Environment.HBM2DDL_AUTO, "create-drop");
            settings.put(Environment.NON_CONTEXTUAL_LOB_CREATION, "true");

            registryBuilder.applySettings(settings);

            registry = registryBuilder.build();

            MetadataSources sources = new MetadataSources(registry)
                                            .addAnnotatedClass(Usuario.class)
                                            .addAnnotatedClass(Equipe.class)
                                            .addAnnotatedClass(Membro.class)
                                            .addAnnotatedClass(Turma.class);
                                            
            Metadata metadata = sources.getMetadataBuilder().build();

            return metadata.getSessionFactoryBuilder().build();
        }
        catch(Throwable e){
            System.err.println("Failed to start SessionFactory." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory(){
        
        if(SINGLE_INSTANCE == null){
            getInstance();
        }

        return sessionFactory;
    }

    public static void shutdown(){
        if(registry != null){
            StandardServiceRegistryBuilder.destroy(registry);   
        }
    }
    
    public static void init() {
    	getSessionFactory();
    }

}
