package com.github.andersori.led.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.github.andersori.led.util.Constante;

public class Database{

    private static Database SINGLE_INSTANCE = null;

    private String drive;
    private String url;
    private String user;
    private String password;
    private String dialect;

    private Database(){
        loadData();
    }

    public static Database getInstance(){
        if(SINGLE_INSTANCE == null){
            synchronized(Database.class){
                if(SINGLE_INSTANCE == null){
                    SINGLE_INSTANCE = new Database();
                }
            }
        }
        return SINGLE_INSTANCE;
    }

    private void loadData(){
        drive = "UNDEFINED";
        url = "UNDEFINED";
        user = "UNDEFINED";
        password ="UNDEFINED";
        dialect = "UNDEFINED";

        try{
            FileReader file = new FileReader(Constante.getDatabaseConfigDirectory());
            BufferedReader reader = new BufferedReader(file);

            String line;

            line = reader.readLine();
            if(line != null){drive = line;}

            line = reader.readLine();
            if(line != null){url = line;}

            line = reader.readLine();
            if(line != null){user = line;}

            line = reader.readLine();
            if(line != null){password = line;}
            
            line = reader.readLine();
            if(line != null){dialect = line;}

            file.close();
            reader.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getDrive(){
        return drive;
    }

    public String getUrl(){
        return url;
    }

    public String getUser(){
        return user;
    }

    public String getPassword(){
        return password;
    }

    public String getDialect(){
        return dialect;
    }

}
