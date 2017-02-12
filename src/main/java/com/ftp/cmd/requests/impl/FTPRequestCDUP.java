package com.ftp.cmd.requests.impl;

import java.io.FileNotFoundException;

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
	public FTPResponse execute(final Context context) {
		try {
			context.getFileSystem().cdup();
		} catch (final FileNotFoundException e) {
			return new FTPResponse(550, "Requested action not taken.");
		}
		return new FTPResponse(200, "directory changed to " + context.getFileSystem().pwd());
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}

}
