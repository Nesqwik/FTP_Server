package com.ftp.database;

import java.util.HashMap;
import java.util.Map;

public class Database {
	private static Database instance = new Database();
	private final Map<String, String> users = new HashMap<>();
	
	private final Map<String, String> userDirectories = new HashMap<>();
	
	private String usersDirectoriesRoot = "directories";
	
	private Database() {
		users.put("admin", "password");
		users.put("toto", "toto");
		users.put("anonymous", "");
		
		setUsersDirectories();
	}
	
	private void setUsersDirectories() {
		for (final String userName : users.keySet()) {
			userDirectories.put(userName, usersDirectoriesRoot + "/" + userName);
		}
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
	
	public boolean logIn(final String username, final String password) {
		return doesUserExist(username) && isPasswordCorrect(username, password);
	}

	public String getUserRootDirectory(final String username) {
		return userDirectories.get(username);
	}

	public void setDirectoriesRoot(final String root) {
		this.usersDirectoriesRoot = root;
		setUsersDirectories();
	}
}
