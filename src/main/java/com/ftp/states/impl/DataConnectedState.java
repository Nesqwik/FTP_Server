package com.ftp.states.impl;

import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestEprt;
import com.ftp.cmd.requests.impl.FTPRequestList;
import com.ftp.states.api.DirNavigationState;
import com.ftp.utils.Context;

public class DataConnectedState extends DirNavigationState {

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
	public void concreteExecuteRequest(final Context context, final FTPRequestEprt request) {
		handleRequest(context, request);
	}
	
	@Override
	protected String getName() {
		return "DataConnected";
	}

}
