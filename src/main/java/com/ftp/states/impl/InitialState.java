package com.ftp.states.impl;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestUser;
import com.ftp.states.StateFactory;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

/**
 * Etat initial. L'utilisateur ne peut que envoyer la commande User.
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class InitialState extends State {
	
	public InitialState() {	}

	@Override
	public void executeRequest(final Context context, final FTPRequest request) {
		super.executeRequest(context, request);
		request.executeState(context, this);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestUser request) {
		final FTPResponse response = request.execute(context);
		
		context.setCurrentState(StateFactory.getUserState());
		context.getClient().sendResponse(response);
	}
	
	@Override
	public String getName() {
		return "initial";
	}
}
