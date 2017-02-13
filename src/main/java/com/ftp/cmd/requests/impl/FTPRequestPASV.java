package com.ftp.cmd.requests.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;
import com.ftp.utils.Parser;


/**
 * Commande FTP PASV 
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestPASV extends FTPRequest {

	public FTPRequestPASV(final String message) {
		this.message = message;
	}

	@Override
	public Commands getCommand() {
		return Commands.PASV;
	}

	@Override
	public FTPResponse execute(final Context context) {
		try {
			final String ipAddress = InetAddress.getLocalHost().getHostAddress();
			final int port = 2021;
			
			context.getClient().setDataAddr(ipAddress);
			context.getClient().setDataPort(port);
			
			final String response = Parser.makePASVResponse(ipAddress, port);
			context.getClient().connectDataSocket();
			
			return new FTPResponse(227, response);
		} catch (final UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}

}
