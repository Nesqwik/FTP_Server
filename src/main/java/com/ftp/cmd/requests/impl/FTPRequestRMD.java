package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;


/**
 * Commande FTP RMD 
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestRMD extends FTPRequest {

	public FTPRequestRMD(String message) {
		super(message);
	}

	@Override
	public Commands getCommand() {
		return Commands.RMD;
	}

	@Override
	public FTPResponse execute(Context context) {
		if (context.getFileSystem().rmd(getMessage())) {
			return new FTPResponse(250, "Requested file action okay, completed.");
		}
		return new FTPResponse(550, "Requested action not taken.");
	}

	@Override
	public void executeState(Context context, State state) {
		state.concreteExecuteRequest(context, this);
	}

}
