package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.database.Database;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

/**
 * Commande FTP PASS 
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestPass extends FTPRequest {
	
	public FTPRequestPass(final String message) {
		this.message = message;
	}

	@Override
	public Commands getCommand() {
		return Commands.PASS;
	}

	@Override
	public FTPResponse execute(final Context context) {
		
		if (Database.getInstance().logIn(context.getUsername(), getMessage())) {
			return new FTPResponse(230, "User logged in");
		}
		
		return new FTPResponse(530, "Not logged in");
	}
	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}

}
