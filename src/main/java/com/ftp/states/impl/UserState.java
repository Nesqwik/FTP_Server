package com.ftp.states.impl;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestPass;
import com.ftp.cmd.requests.impl.FTPRequestQuit;
import com.ftp.database.Database;
import com.ftp.states.StateFactory;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

public class UserState extends State {

	@Override
	public void executeRequest(final Context context, final FTPRequest request) {
		super.executeRequest(context, request);
		request.executeState(context, this);
	}

	@Override
	public String getName() {
		return "user";
	}

	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestPass request) {
		final FTPResponse response = request.execute(context);
		
		if(response.getCode() == 230) {
			final String rootDirectory = Database.getInstance().getUserRootDirectory(context.getUsername());
			context.setFileSystem(rootDirectory);
			context.setCurrentState(StateFactory.getLoggedInState());
		}
		
		context.getClient().sendResponse(response);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestQuit request) {
		request.execute(context);
	}

}
