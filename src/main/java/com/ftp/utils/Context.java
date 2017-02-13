package com.ftp.utils;

import com.ftp.Client;
import com.ftp.states.StateFactory;
import com.ftp.states.api.State;

public class Context {
	
	private Client client;
	private State currentState;
	private State previousState = null;
	private String username = null;
	private FileSystem fileSystem;
	
	private Thread passiveConnectionThread = null;
	
	public Context(final Client client) {
		this.client = client;
		this.currentState = StateFactory.getInitialState();
	}
	
	public Thread getThread() {
		return passiveConnectionThread;
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
	public void setClient(final Client client) {
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
	public void setCurrentState(final State currentState) {
		previousState = this.currentState;
		this.currentState = currentState;
		System.out.println("current state is " + this.currentState.getName());
	}
	
	public State getPreviousState() {
		return previousState;
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
	public void setUsername(final String username) {
		this.username = username;
	}

	public FileSystem getFileSystem() {
		return fileSystem;
	}

	public void setFileSystem(final String rootDirectory) {
		this.fileSystem = new FileSystem(rootDirectory);
	}


	public void joinConnectionThreadIfAlive() throws InterruptedException {
		if (passiveConnectionThread.isAlive()) {
			System.out.println("joining " + passiveConnectionThread);
			passiveConnectionThread.join();
			System.out.println("joined");
		}
	}


	public void setPassiveConnectionThread(final Thread passiveConnectionThread) {
		this.passiveConnectionThread = passiveConnectionThread;
	}
}
