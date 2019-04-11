package com.github.andersori.led.util;

import java.util.Map;

import com.github.shyiko.dotenv.DotEnv;

public class Constante {
	
	private static String DATABASE_CONFIG_DIRECTORY;
	private static String APP_NAME;
	private static String APP_URL;
	private static String APP_URL_JS;
	private static String APP_URL_CSS;
	private static String APP_URL_IMG;
	
	static {
		Map<String, String> dotEnv = DotEnv.load();
		
		Constante.DATABASE_CONFIG_DIRECTORY = dotEnv.get("DATABASE_CONFIG_DIRECTORY");
		Constante.APP_NAME = dotEnv.get("APP_NAME");
		Constante.APP_URL = dotEnv.get("APP_URL");
		Constante.APP_URL_JS = dotEnv.get("APP_URL_JS");
		Constante.APP_URL_CSS = dotEnv.get("APP_URL_CSS");
		Constante.APP_URL_IMG = dotEnv.get("APP_URL_IMG");
		
	}
	
	public static String getDatabaseConfigDirectory() {
		return Constante.DATABASE_CONFIG_DIRECTORY;
	}
	
	public static String getAppName() {
		return Constante.APP_NAME;
	}
	
	public static String getUrl() {
		return Constante.APP_URL;
	}
	
	public static String getUrlJs() {
		return Constante.APP_URL_JS;
	}
	
	public static String getUrlCss() {
		return Constante.APP_URL_CSS;
	}

	public static String getUrlImg() {
		return Constante.APP_URL_IMG;
	}
	
}
