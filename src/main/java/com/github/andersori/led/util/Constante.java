package com.github.andersori.led.util;

import java.util.Map;

import com.github.shyiko.dotenv.DotEnv;

public class Constante {
	
	private static String DATABASE_CONFIG_DIRECTORY;
	private static String APP_NAME;
	private static String APP_URL;
	
	static {
		Map<String, String> dotEnv = DotEnv.load();
		
		Constante.DATABASE_CONFIG_DIRECTORY = dotEnv.get("DATABASE_CONFIG_DIRECTORY");
		Constante.APP_NAME = dotEnv.get("APP_NAME");
		Constante.APP_URL = dotEnv.get("APP_URL");
		
	}
	
	public static String getDatabaseConfigDirectory() {
		return Constante.DATABASE_CONFIG_DIRECTORY;
	}
	
	public static String getAppName() {
		return Constante.APP_NAME;
	}
	
	public static String getAppUrl(){
		return Constante.APP_URL;
	}
	
}
