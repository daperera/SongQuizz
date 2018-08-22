package com.telecom.game;

public class User {

	public User(String username) {
		// TODO Auto-generated constructor stub
	}

	public void welcomeMessage() {
		blank(2);
	}

	public static void blank() {
		System.out.println("");
	}
	
	public static void blank(int n) {
		for(int k=0; k<n; k++)
			System.out.println("");
	}
	
}
