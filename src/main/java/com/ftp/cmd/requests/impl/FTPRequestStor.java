package com.ftp.cmd.requests.impl;

import java.io.DataInputStream;
import java.io.IOException;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

public class FTPRequestStor extends FTPRequest {
	
	public FTPRequestStor(final String message) {
		this.message = message;
	}

	@Override
	public Commands getCommand() {
		return Commands.STOR;
	}

	@Override
	public FTPResponse execute(final Context context) {
		context.getClient().sendResponse(new FTPResponse(150, "File status okay; about to open data connection."));
		//context.getClient().connectDataSocket();
		
		try {
			final DataInputStream dis = context.getClient().getDataInputStream();
			context.getFileSystem().writeFileToSystem(getMessage(), dis);
			//dis.close();
			//context.getClient().closeDataSocket();
			return new FTPResponse(226, "Closing data connection. Requested file action successful.");
		} catch (final IOException e) {
			return new FTPResponse(451, "Requested action aborted: local error in processing.");
		}
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}
}
