package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

/**
 * Commande FTP MKD 
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestMKD extends FTPRequest {

	public FTPRequestMKD(String message) {
		super(message);
	}

	@Override
	public Commands getCommand() {
		return Commands.MKD;
	}

	@Override
	public FTPResponse execute(Context context) {
		String newFile = getMessage();
		if (context.getFileSystem().mkd(newFile)) {
			return new FTPResponse(257, "\"" + getMessage() + "\" directory created");
		}
		
		return new FTPResponse(550, "Requested action not taken.");
	}

	@Override
	public void executeState(Context context, State state) {
		state.concreteExecuteRequest(context, this);
	}

}
