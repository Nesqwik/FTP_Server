package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

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
		
		//TODO: handle password verification
		return new FTPResponse(230, "User logged in");
	}
	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}

}
