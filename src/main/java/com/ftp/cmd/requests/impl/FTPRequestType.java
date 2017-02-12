package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

public class FTPRequestType extends FTPRequest {
	@Override
	public Commands getCommand() {
		return Commands.TYPE;
	}

	@Override
	public FTPResponse execute(Context context) {
		return new FTPResponse(200, "ASCII Non-print");
	}

	@Override
	public void executeState(Context context, State state) {
		state.concreteExecuteRequest(context, this);
	}

}
