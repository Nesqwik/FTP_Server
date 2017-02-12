package com.ftp.database;

import java.util.HashMap;
import java.util.Map;

public class Database {
	private static Database instance = new Database();
	private final Map<String, String> users = new HashMap<>();
	
	private final Map<String, String> userDirectories = new HashMap<>();
	
	private Database() {
		users.put("admin", "password");
		users.put("toto", "toto");
		users.put("anonymous", "");
				
		userDirectories.put("admin", "/directories/admin");
		userDirectories.put("toto", "/directories/toto");
		userDirectories.put("anonymous", "/directories/anonymous");
	}
	
	public static Database getInstance() {
		return instance;
	}
	
	public boolean isPasswordCorrect(final String user, final String password) {
		if(user.equals("anonymous")) {
			return !password.isEmpty();
		} else {
			return users.get(user).equals(password);
		}
	}
	
	public boolean doesUserExist(final String user) {
		return users.containsKey(user);
	}

	public String getUserRootDirectory(final String username) {
		return userDirectories.get(username);
	}
}
