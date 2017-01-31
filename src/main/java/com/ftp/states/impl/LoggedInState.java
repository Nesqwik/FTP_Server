package com.ftp.states.impl;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestSyst;
import com.ftp.states.State;
import com.ftp.utils.Context;

public class LoggedInState extends State {

	@Override
	public void executeRequest(Context context, FTPRequest request) {
		super.executeRequest(context, request);
		request.executeState(context, this);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestSyst request) {
		handleRequest(context, request);
	}
	
	// TODO : Ajouter command PORT + passer état DataConnected
	
	
	private void handleRequest(Context context, FTPRequest request) {
		FTPResponse response = request.execute(context);
		context.getClient().sendResponse(response);
	}
	
	@Override
	protected String getName() {
		return "LoggedIn";
	}

}
