package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;


/**
 * Commande FTP QUIT : permet de se déconnecter du serveur
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestQuit extends FTPRequest {

	@Override
	public Commands getCommand() {
		return Commands.QUIT;
	}

	@Override
	public FTPResponse execute(Context context) {
		return new FTPResponse(221, "Goodbye.");
	}

	public void executeState(Context context, State state) {
		state.concreteExecuteRequest(context, this);
	}
}
