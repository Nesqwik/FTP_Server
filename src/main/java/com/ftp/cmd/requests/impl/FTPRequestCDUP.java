package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.State;
import com.ftp.utils.Context;

public class FTPRequestCDUP extends FTPRequest {
	
	@Override
	public Commands getCommand() {
		return Commands.CDUP;
	}

	@Override
	public FTPResponse execute(Context context) {
		context.getFileSystem().cdup();
		return new FTPResponse(200, "directory changed to " + context.getFileSystem().pwd());
	}

	@Override
	public void executeState(Context context, State state) {
		state.concreteExecuteRequest(context, this);
	}

}
