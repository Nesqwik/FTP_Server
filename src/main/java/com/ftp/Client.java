package com.ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.ParseException;

import com.ftp.cmd.FTPRequest;
import com.ftp.cmd.FTPResponse;
import com.ftp.states.InitialState;
import com.ftp.states.State;

public class Client implements Runnable {
	private Socket client;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private State currentState;
	
	private String username = null;
	
	public Client(Socket client) throws IOException {
		this.client = client;
		reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		currentState = new InitialState();
	}
	
	public void run() {		
		try {
			String request = "";
			while((request = reader.readLine()) != null) {
				try {
					FTPRequest ftpRequest = Parser.parseRequest(request);
					
					currentState.executeRequest(this, ftpRequest);
				} catch (ParseException e) {
					// TODO: return bad request
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void setState(State newState) {
		currentState = newState;
	}
	
	public void sendResponse(FTPResponse response) {
		try {
			writer.write(response.toString());
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void quit() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
