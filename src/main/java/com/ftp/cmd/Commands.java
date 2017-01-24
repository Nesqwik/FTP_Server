package com.ftp.cmd;

public enum Commands {
	
	USER("USER"),
	PASS("PASS"),
	LIST("LIST"),
	RETR("RETR"),
	STOR("STOR"),
	QUIT("QUIT");
	
	private String name;
	
	Commands(String name) {
		this.name = name;
	}

}
