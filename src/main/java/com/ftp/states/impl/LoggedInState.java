package com.ftp.states.impl;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestEprt;
import com.ftp.cmd.requests.impl.FTPRequestEpsv;
import com.ftp.cmd.requests.impl.FTPRequestPASV;
import com.ftp.cmd.requests.impl.FTPRequestPort;
import com.ftp.cmd.requests.impl.FTPRequestSyst;
import com.ftp.cmd.requests.impl.FTPRequestType;
import com.ftp.states.StateFactory;
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
		
		if (response.getCode() == 200) {
			context.setCurrentState(new DataConnectedState());
		}
		
		context.getClient().sendResponse(response);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestPort request) {
		final FTPResponse response = request.execute(context);
		
		if (response.getCode() == 200) {
			context.setCurrentState(new DataConnectedState());
		}
		
		context.getClient().sendResponse(response);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestPASV request) {
		//TODO: check if useful, and if so, make it work
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestEpsv request) {
		//TODO: handle errors
		final FTPResponse response = request.execute(context);
		context.setCurrentState(StateFactory.getPassiveDataConnectedState());
		context.getClient().sendResponse(response);
	}
	
	@Override
	public String getName() {
		return "LoggedIn";
	}

}
