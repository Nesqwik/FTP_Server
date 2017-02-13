package com.ftp.cmd.requests.impl;

import java.io.IOException;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

/**
 * Commande FTP CWD (change directory)
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestCWD extends  FTPRequest {

	public FTPRequestCWD(final String message) {
		this.message = message;
	}
	
	@Override
	public Commands getCommand() {
		return Commands.CWD;
	}

	@Override
	public FTPResponse execute(final Context context) {
		try {
			context.getFileSystem().cwd(getMessage());
		} catch (final IOException e) {
			return new FTPResponse(550, "Requested action not taken.");
		}
		return new FTPResponse(250, "Directory successfully changed.");
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}
	
}