package com.ftp.cmd.requests.impl;

import java.net.InetSocketAddress;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;
import com.ftp.utils.Parser;

public class FTPRequestEprt extends FTPRequest {

	public FTPRequestEprt(String message) {
		this.message = message;
	}
	
	@Override
	public Commands getCommand() {
		return Commands.EPRT;
	}

	@Override
	public FTPResponse execute(Context context) {
		InetSocketAddress socketAddr = Parser.parseEPRT(getMessage());
		
		context.getClient().setDataAddr(socketAddr.getHostString());
		context.getClient().setDataPort(socketAddr.getPort());
		return new FTPResponse(200, "ok");
	}

	public void executeState(Context context, State state) {
		state.concreteExecuteRequest(context, this);
	}

}
