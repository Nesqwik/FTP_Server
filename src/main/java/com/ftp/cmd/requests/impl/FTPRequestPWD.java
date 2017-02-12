package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

public class FTPRequestPWD extends FTPRequest {

	@Override
	public Commands getCommand() {
		return Commands.PWD;
	}

	@Override
	public FTPResponse execute(final Context context) {
		return new FTPResponse(257, context.getFileSystem().pwd());
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}

}
