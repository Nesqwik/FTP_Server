package com.ftp.states.impl;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestRnto;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

/**
 * Etat dans lequel le serveur a enregistré le nom du fichier à renommer. il est maintenant prêt à reçevoir la cible du renommage. 
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class RenamingState extends State {
	
	public RenamingState() { }
	
	private String fileToRenamePath;

	@Override
	public String getName() {
		return "RenamingState";
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestRnto request) {
		request.setFileToRenamePath(fileToRenamePath);
		final FTPResponse response = request.execute(context);
		
		context.setCurrentState(context.getPreviousState());
		
		context.getClient().sendResponse(response);
	}
	
	@Override
	protected void onNotImplementedResponse(final Context context) {
		final FTPResponse response = new FTPResponse(503, "Bad sequence of commands.");
		context.setCurrentState(context.getPreviousState());
		context.getClient().sendResponse(response);
	}
	
	@Override
	public void executeRequest(final Context context, final FTPRequest request) {
		super.executeRequest(context, request);
		request.executeState(context, this);
	}

	public void setFileToRename(final String fileToRenamePath) {
		this.fileToRenamePath = fileToRenamePath;
	}

}
