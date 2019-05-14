package com.github.andersori.led.util;

import java.util.Map;

import com.github.shyiko.dotenv.DotEnv;

public class Constante {
	
	private static String APP_NAME;
	private static String APP_URL;
	private static String DB_DRIVE;
	private static String DB_DIALECT;
	
	static {
		Map<String, String> dotEnv = DotEnv.load();
		
		Constante.APP_NAME = dotEnv.get("APP_NAME");
		Constante.DB_DRIVE = dotEnv.get("DB_DRIVE");
		Constante.DB_DIALECT = dotEnv.get("DB_DIALECT");
		
	}
	
	public static String getAppName() {
		return Constante.APP_NAME;
	}
	
	public static String getAppUrl(){
		return Constante.APP_URL;
	}

	public static String getDbDrive() {
		return DB_DRIVE;
	}

	public static String getDbDialect() {
		return DB_DIALECT;
	}
	
}
