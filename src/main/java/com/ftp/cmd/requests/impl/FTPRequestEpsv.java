package com.ftp.cmd.requests.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

/**
 * Commande FTP EPSV 
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestEpsv extends FTPRequest {

	public FTPRequestEpsv(final String message) {
		super(message);
	}

	@Override
	public FTPResponse execute(final Context context) {
//		try {
//			final InetSocketAddress socketAddr = Parser.parsePORT(getMessage());
//			
			try {
				context.getClient().setDataAddr(InetAddress.getLocalHost().getHostAddress());
				context.getClient().connectPassiveDataSocket();
				
				final int dataPort = context.getClient().getDataPort();
				
				return new FTPResponse(229, "Entering Extended Passive Mode (|||" + dataPort + "|).");
			} catch (final UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			return new FTPResponse(200, "Command okay.");
//		} catch (final ParseException e) {
//			return new FTPResponse(501, "Syntax error in parameters or arguments.");
//		}
			return null;
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}

	@Override
	public Commands getCommand() {
		return Commands.EPSV;
	}
}
