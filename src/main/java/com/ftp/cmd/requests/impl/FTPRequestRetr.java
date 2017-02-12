package com.ftp.cmd.requests.impl;

import java.io.DataOutputStream;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.State;
import com.ftp.utils.Context;

public class FTPRequestRetr extends FTPRequest {

	public FTPRequestRetr(final String message) {
		this.message = message;
	}
	
	@Override
	public Commands getCommand() {
		return Commands.RETR;
	}

	@Override
	public FTPResponse execute(final Context context) {
		context.getClient().sendResponse(new FTPResponse(150, "File status okay; about to open data connection."));
		context.getClient().connectDataSocket();
		
		final DataOutputStream dos = context.getClient().getDataOutputStream();
		context.getFileSystem().writeFileToBuffer(getMessage(), dos);
		
		context.getClient().closeDataSocket();
		return new FTPResponse(200, "ok");
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}
}
