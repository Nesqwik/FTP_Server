package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;


/**
 * Commande FTP RNFR 
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestRnfr extends FTPRequest {

	public FTPRequestRnfr(final String message) {
		super(message);
	}
	
	@Override
	public Commands getCommand() {
		return Commands.RNFR;
	}

	@Override
	public FTPResponse execute(final Context context) {
		final String fileToRenamePath = getMessage();
		if (context.getFileSystem().doesFileExist(fileToRenamePath)) {
			return new FTPResponse(350, "Requested file action pending further information."); 
		}
		return new FTPResponse(550, "Requested action not taken.\nFile unavailable (e.g., file not found, no access).");
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}

}
