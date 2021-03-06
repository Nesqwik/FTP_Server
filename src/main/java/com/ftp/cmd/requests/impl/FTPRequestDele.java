package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

/**
 * Commande FTP DELE : suppression de fichier ou dossier
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestDele extends FTPRequest {

	public FTPRequestDele(final String message) {
		super(message);
	}
	
	@Override
	public Commands getCommand() {
		return Commands.DELE;
	}

	@Override
	public FTPResponse execute(final Context context) {
		if(!context.getFileSystem().dele(getMessage())) {
			return new FTPResponse(550, "Requested action not taken.");
		}
		return new FTPResponse(250, "Requested file action okay, completed.");
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}

}
