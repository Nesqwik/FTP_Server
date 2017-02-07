package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.State;
import com.ftp.utils.Context;

public class FTPRequestUser extends FTPRequest {
	
	public FTPRequestUser(String message) {
		this.message = message;
	}

	@Override
	public Commands getCommand() {
		return Commands.USER;
	}

	@Override
	public FTPResponse execute(Context context) {
		// TODO: handle the login (check existance...)
		
		String username = getMessage();
		
		context.setUsername(username);
		return new FTPResponse(331, "User name ok, need password");
	}

	public void executeState(Context context, State state) {
		state.concreteExecuteRequest(context, this);
	}
}
