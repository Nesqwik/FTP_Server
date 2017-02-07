package com.ftp.database;

import java.util.HashMap;
import java.util.Map;

public class Database {
	private static Database instance = new Database();
	private Map<String, String> users = new HashMap<>();
	
	private Map<String, String> userDirectories = new HashMap<>();
	
	private Database() {
		users.put("admin", "password");
		users.put("toto", "toto");
		users.put("anonymous", "");
		
		String root = System.getProperty("user.dir");
		
		userDirectories.put("admin", root + "/directories/admin");
		userDirectories.put("toto", root + "/directories/toto");
		userDirectories.put("anonymous", root + "/directories/anonymous");
	}
	
	public static Database getInstance() {
		return instance;
	}
	
	public boolean isPasswordCorrect(String user, String password) {
		if(user.equals("anonymous")) {
			return !password.isEmpty();
		} else {
			return users.get(user).equals(password);
		}
	}
	
	public boolean doesUserExist(String user) {
		return users.containsKey(user);
	}

	public String getUserRootDirectory(String username) {
		return userDirectories.get(username);
	}
}
