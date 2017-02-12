package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

public class FTPRequestUser extends FTPRequest {
	
	public FTPRequestUser(final String message) {
		this.message = message;
	}

	@Override
	public Commands getCommand() {
		return Commands.USER;
	}

	@Override
	public FTPResponse execute(final Context context) {
		final String username = getMessage();
		
		context.setUsername(username);
		return new FTPResponse(331, "User name ok, need password");
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}
}
