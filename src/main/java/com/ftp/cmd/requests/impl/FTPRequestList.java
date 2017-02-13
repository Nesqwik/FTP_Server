package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

/**
 * Commande FTP LIST : permet de lister le contenu d'un dossier
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestList extends FTPRequest {

	@Override
	public Commands getCommand() {
		return Commands.LIST;
	}

	@Override
	public FTPResponse execute(final Context context) {
	    context.getClient().sendResponse(new FTPResponse(150, "Here comes the directory listing."));
		
	    try {
			context.joinConnectionThreadIfAlive();
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
	    context.getClient().sendStringData(context.getFileSystem().list());
	    
		return new FTPResponse(226, "Directory send OK.");
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}
}
