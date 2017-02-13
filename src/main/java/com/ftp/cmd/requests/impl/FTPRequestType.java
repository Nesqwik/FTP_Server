package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

/**
 * Commande FTP TYPE 
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPRequestType extends FTPRequest {
	@Override
	public Commands getCommand() {
		return Commands.TYPE;
	}

	@Override
	public FTPResponse execute(final Context context) {
		return new FTPResponse(200, "ok");
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}

}
