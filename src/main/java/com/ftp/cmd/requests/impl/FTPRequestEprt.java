package com.ftp.cmd.requests.impl;

import java.net.InetSocketAddress;
import java.text.ParseException;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;
import com.ftp.utils.Parser;

/**
 * Commande FTP EPRT : permet la configuration de la socket en mode actif
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestEprt extends FTPRequest {

	public FTPRequestEprt(final String message) {
		this.message = message;
	}
	
	@Override
	public Commands getCommand() {
		return Commands.EPRT;
	}

	@Override
	public FTPResponse execute(final Context context) {
		try {
			final InetSocketAddress socketAddr = Parser.parseEPRT(getMessage());
			context.getClient().setDataAddr(socketAddr.getHostString());
			context.getClient().setDataPort(socketAddr.getPort());
			return new FTPResponse(200, "ok");
		} catch (final ParseException e) {
			return new FTPResponse(501, "Syntax error in parameters or arguments.");
		}
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}

}
