package com.ftp.states.impl;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestCDUP;
import com.ftp.cmd.requests.impl.FTPRequestDele;
import com.ftp.cmd.requests.impl.FTPRequestEprt;
import com.ftp.cmd.requests.impl.FTPRequestSyst;
import com.ftp.cmd.requests.impl.FTPRequestType;
import com.ftp.states.api.DirNavigationState;
import com.ftp.utils.Context;

public class LoggedInState extends DirNavigationState {

	@Override
	public void executeRequest(final Context context, final FTPRequest request) {
		super.executeRequest(context, request);
		request.executeState(context, this);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestSyst request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestType request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestEprt request) {
		final FTPResponse response = request.execute(context);
		context.setCurrentState(new DataConnectedState());
		context.getClient().sendResponse(response);
	}
	
	// TODO : Ajouter command PORT + passer état DataConnected
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestCDUP request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestDele request) {
		handleRequest(context, request);
	}
	
	@Override
	protected String getName() {
		return "LoggedIn";
	}

}
