package com.ftp.cmd.requests.impl;

import java.net.InetSocketAddress;
import java.text.ParseException;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;
import com.ftp.utils.Parser;

public class FTPRequestPort extends FTPRequest {

	public FTPRequestPort(final String message) {
		super(message);
	}

	@Override
	public FTPResponse execute(final Context context) {
		try {
			final InetSocketAddress socketAddr = Parser.parsePORT(getMessage());
			
			context.getClient().setDataAddr(socketAddr.getHostString());
			context.getClient().setDataPort(socketAddr.getPort());
			return new FTPResponse(200, "Command okay.");
		} catch (final ParseException e) {
			return new FTPResponse(501, "Syntax error in parameters or arguments.");
		}
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}

	@Override
	public Commands getCommand() {
		return Commands.PORT;
	}
}
