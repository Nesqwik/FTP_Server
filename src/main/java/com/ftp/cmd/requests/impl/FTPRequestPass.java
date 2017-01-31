package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.State;
import com.ftp.utils.Context;

public class FTPRequestPass extends FTPRequest {
	
	public FTPRequestPass(String message) {
		this.message = message;
	}

	@Override
	public Commands getCommand() {
		return Commands.PASS;
	}

	@Override
	public FTPResponse execute(Context context) {
		return new FTPResponse(230, "User logged in");
	}
	public void executeState(Context context, State state) {
		state.concreteExecuteRequest(context, this);
	}

}