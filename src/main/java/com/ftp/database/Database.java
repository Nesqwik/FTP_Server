package com.ftp.database;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe permettant de "simuler" une base de données des utilisateurs.
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
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
	
	/**
	 * 
	 * @return l'instance de la base de données.
	 */
	public static Database getInstance() {
		return instance;
	}
	
	/**
	 * Vérifie si le mot de passe est correct pour l'utilisateur donné
	 * @param user l'utilisateur à vérifier
	 * @param password le mot de passe relatif à l'utilisateur
	 * @return vrai si l'utilisateur est associé au mot de passe. Faux sinon.
	 */
	public boolean isPasswordCorrect(final String user, final String password) {
		if(user.equals("anonymous")) {
			return !password.isEmpty();
		} else {
			return users.get(user).equals(password);
		}
	}
	
	/**
	 * Vérifie si l'utilisateur existe.
	 * @param user l'utilisateur à vérifier
	 * @return vrai si l'utilisateur existe. Faux sinon.
	 */
	public boolean doesUserExist(final String user) {
		return users.containsKey(user);
	}
	
	/**
	 * @param username nom d'utilisateur
	 * @param password mot de passe de l'utilisateur
	 * @return vrai si l'utilisateur existe et que le mot de passe associé est le bon. Faux sinon.
	 */
	public boolean logIn(final String username, final String password) {
		return doesUserExist(username) && isPasswordCorrect(username, password);
	}

	/**
	 * Renvoir le root directory de l'utilisateur
	 * @param username le nom d'utilisateur.
	 * @return Le root directory ftp de l'utilisateur.
	 */
	public String getUserRootDirectory(final String username) {
		return userDirectories.get(username);
	}

	/**
	 * Permet de définir un root directory.
	 * @param root
	 */
	public void setDirectoriesRoot(final String root) {
		this.usersDirectoriesRoot = root;
		setUsersDirectories();
	}
}
