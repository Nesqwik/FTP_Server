package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

/**
 * Commande FTP RNTO : permet de préciser le nouveau nom du fichier à renommer. Doit être
 * précédée d'une requête RNFR pour fonctionner.
 * 
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestRnto extends FTPRequest {
	
	protected String fileToRenamePath = null;
	
	public FTPRequestRnto(final String message) {
		super(message);
	}

	@Override
	public Commands getCommand() {
		return Commands.RNTO;
	}

	@Override
	public FTPResponse execute(final Context context) {
		if (context.getFileSystem().rename(fileToRenamePath, getMessage())){
			return new FTPResponse(250, "Requested file action okay, completed.");
		}
		return new FTPResponse(550, "Requested action not taken.\nFile unavailable (e.g., file not found, no access).");
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}
	
	public String getFileToRenamePath() {
		return fileToRenamePath;
	}

	public void setFileToRenamePath(final String fileToRenamePath) {
		this.fileToRenamePath = fileToRenamePath;
	}


}
