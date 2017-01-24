package com.ftp.states;

import com.ftp.Client;
import com.ftp.cmd.FTPRequest;

public class InitialState implements State {
	
	public InitialState() {
		
	}

	@Override
	public void executeRequest(Client client, FTPRequest request) {
		if(request.getCode().equals("USER")) {
			request.execute(client);
			client.setState(new UserState());
		} else if(request.getCode().equals("QUIT")) {
			client.quit();
		} else {
			//TODO: gérer erreur
		}
	}

}
