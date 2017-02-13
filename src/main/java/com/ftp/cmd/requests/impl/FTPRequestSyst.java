package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

/**
 * Commande FTP SYST
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestSyst extends FTPRequest {

	@Override
	public Commands getCommand() {
		return Commands.SYST;
	}

	@Override
	public FTPResponse execute(Context context) {
		return new FTPResponse(215, "UNIX");
	}

	@Override
	public void executeState(Context context, State state) {
		state.concreteExecuteRequest(context, this);
	}

}
