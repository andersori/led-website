package com.github.andersori.led;

import org.mindrot.jbcrypt.BCrypt;

public class AppTest{
    
	public static void main(String[] args) {
		System.out.println((BCrypt.hashpw("1234", BCrypt.gensalt())));
		
	}

}
