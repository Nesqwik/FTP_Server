package com.ftp.states.api;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.impl.FTPRequestCDUP;
import com.ftp.cmd.requests.impl.FTPRequestCWD;
import com.ftp.cmd.requests.impl.FTPRequestDele;
import com.ftp.cmd.requests.impl.FTPRequestMKD;
import com.ftp.cmd.requests.impl.FTPRequestPWD;
import com.ftp.cmd.requests.impl.FTPRequestRMD;
import com.ftp.cmd.requests.impl.FTPRequestRnfr;
import com.ftp.states.StateFactory;
import com.ftp.utils.Context;

/**
 * Etat contenant toutes les opérations relatives à la gestion des fichiers sur le serveur
 * ne nécessitant pas de connexion faisant transiter des données.
 * 
 * Il a été crée pour regrouper les comportements similaires des états LoggedInState, 
 * DataConnectedState et PassiveDataConnectedState.
 * 
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public abstract class DirNavigationState extends State {

	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestMKD request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestRMD request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestCDUP request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestCWD request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestPWD request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestDele request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestRnfr request) {
		final FTPResponse response = request.execute(context);
		
		if (response.getCode() == 350) {
			context.setCurrentState(StateFactory.getRenamingState(request.getMessage()));
		}
		
		context.getClient().sendResponse(response);
	}
}
