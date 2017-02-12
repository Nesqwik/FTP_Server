package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

public class FTPRequestCWD extends  FTPRequest {

	public FTPRequestCWD(String message) {
		this.message = message;
	}
	
	@Override
	public Commands getCommand() {
		return Commands.CWD;
	}

	@Override
	public FTPResponse execute(Context context) {
		context.getFileSystem().cwd(getMessage());
		return new FTPResponse(250, "Directory successfully changed.");
	}

	public void executeState(Context context, State state) {
		state.concreteExecuteRequest(context, this);
	}
	
}