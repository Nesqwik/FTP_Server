package com.ftp.states.impl;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestEpsv;
import com.ftp.cmd.requests.impl.FTPRequestList;
import com.ftp.cmd.requests.impl.FTPRequestPort;
import com.ftp.cmd.requests.impl.FTPRequestRetr;
import com.ftp.cmd.requests.impl.FTPRequestStor;
import com.ftp.cmd.requests.impl.FTPRequestType;
import com.ftp.states.StateFactory;
import com.ftp.states.api.DirNavigationState;
import com.ftp.utils.Context;

/**
 * State dans lequel l'utilisateur est connecté et le serveur connait son ip/port en mode actif.
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class DataConnectedState extends DirNavigationState {

	@Override
	public void executeRequest(final Context context, final FTPRequest request) {
		super.executeRequest(context, request);
		request.executeState(context, this);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestList request) {
		context.getClient().connectDataSocket();
		handleRequest(context, request);
		context.getClient().closeDataSocket();
	}
	
	@Override
	protected void handleRequest(final Context context, final FTPRequest request) {
		final FTPResponse response = request.execute(context);
		//TODO: check errors
		context.setCurrentState(StateFactory.getLoggedInState());
		context.getClient().sendResponse(response);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestPort request) {
		//TODO: check if useful
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestRetr request) {
		context.getClient().connectDataSocket();
		handleRequest(context, request);
		context.getClient().closeDataSocket();
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestType request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestStor request) {
		context.getClient().connectDataSocket();
		handleRequest(context, request);
		context.getClient().closeDataSocket();
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestEpsv request) {
		try {
			final FTPResponse response = request.execute(context);
			context.setCurrentState(StateFactory.getPassiveDataConnectedState());
			context.getClient().sendResponse(response);			
		} catch (final RuntimeException e) {
			context.getClient().sendResponse(new FTPResponse(501, "Syntax error in parameters or arguments."));
		}
	}
	
	@Override
	public String getName() {
		return "DataConnected";
	}

}
