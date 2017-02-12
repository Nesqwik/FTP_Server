package com.ftp.states.impl;

import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestDele;
import com.ftp.cmd.requests.impl.FTPRequestEprt;
import com.ftp.cmd.requests.impl.FTPRequestList;
import com.ftp.cmd.requests.impl.FTPRequestPort;
import com.ftp.cmd.requests.impl.FTPRequestRetr;
import com.ftp.cmd.requests.impl.FTPRequestStor;
import com.ftp.cmd.requests.impl.FTPRequestType;
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
	public void concreteExecuteRequest(final Context context, final FTPRequestPort request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestRetr request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestType request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestStor request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestDele request) {
		handleRequest(context, request);
	}
	
	@Override
	public String getName() {
		return "DataConnected";
	}

}
