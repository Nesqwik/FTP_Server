package com.ftp.states.impl;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestEprt;
import com.ftp.cmd.requests.impl.FTPRequestList;
import com.ftp.cmd.requests.impl.FTPRequestPort;
import com.ftp.cmd.requests.impl.FTPRequestRetr;
import com.ftp.cmd.requests.impl.FTPRequestStor;
import com.ftp.cmd.requests.impl.FTPRequestType;
import com.ftp.states.StateFactory;
import com.ftp.states.api.DirNavigationState;
import com.ftp.utils.Context;

/**
 * Etat dans lequel le serveur a envoyé les information de connexion en mode passif.
 * L'utilisateur peut envoyer des requêtes nécéssitant le canal de donnée.
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class PassiveDataConnectedState extends DirNavigationState {

	@Override
	public void executeRequest(final Context context, final FTPRequest request) {
		super.executeRequest(context, request);
		request.executeState(context, this);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestList request) {
		handleRequest(context, request);
	}
	
	@Override
	protected void handleRequest(final Context context, final FTPRequest request) {
		try {
			context.joinConnectionThreadIfAlive();
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
		final FTPResponse response = request.execute(context);
		//TODO: check errors in response
		context.setCurrentState(StateFactory.getLoggedInState());
		context.getClient().sendResponse(response);
		context.getClient().closeDataSocket();
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestEprt request) {
		//TODO: check if useful
		final FTPResponse response = request.execute(context);
		
		if (response.getCode() == 200) {
			context.setCurrentState(new DataConnectedState());
		}
		
		context.getClient().sendResponse(response);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestPort request) {
		//TODO: check if useful
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestRetr request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestType request) {
		//TODO : check if still works with comeback to loggedinstate in handleRequest
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestStor request) {
		handleRequest(context, request);
	}
	
	@Override
	public String getName() {
		return "PassiveDataConnected";
	}
}
