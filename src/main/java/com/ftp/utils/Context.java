package com.ftp.utils;

import com.ftp.Client;
import com.ftp.states.State;
import com.ftp.states.impl.InitialState;

public class Context {
	
	private Client client;
	private State currentState;
	private String username = null;
	private FileSystem fileSystem;
	
	public Context(Client client) {
		this.client = client;
		this.currentState = new InitialState();
	}
	
			
	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the currentState
	 */
	public State getCurrentState() {
		return currentState;
	}

	/**
	 * @param currentState the currentState to set
	 */
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public FileSystem getFileSystem() {
		return fileSystem;
	}

	public void setFileSystem(String rootDirectory) {
		this.fileSystem = new FileSystem(rootDirectory);
	}


}
