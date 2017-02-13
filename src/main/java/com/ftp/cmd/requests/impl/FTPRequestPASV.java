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
			String ipAddress = InetAddress.getLocalHost().getHostAddress();
			int port = 2021;
			
			context.getClient().setDataAddr(ipAddress);
			context.getClient().setDataPort(port);
			
			String response = Parser.makePASVResponse(ipAddress, port);
			context.getClient().connectDataSocket();
			
			return new FTPResponse(227, response);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		
//		if (Database.getInstance().logIn(context.getUsername(), getMessage())) {
//			return new FTPResponse(230, "User logged in");
//		}
		return null;
//		return new FTPResponse(530, "Not logged in");
	}
	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}

}
