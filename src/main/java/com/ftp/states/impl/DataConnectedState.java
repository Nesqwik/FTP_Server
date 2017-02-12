package com.ftp.states.impl;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestCDUP;
import com.ftp.cmd.requests.impl.FTPRequestCWD;
import com.ftp.cmd.requests.impl.FTPRequestDele;
import com.ftp.cmd.requests.impl.FTPRequestEprt;
import com.ftp.cmd.requests.impl.FTPRequestList;
import com.ftp.cmd.requests.impl.FTPRequestMKD;
import com.ftp.cmd.requests.impl.FTPRequestPwd;
import com.ftp.cmd.requests.impl.FTPRequestRMD;
import com.ftp.cmd.requests.impl.FTPRequestRetr;
import com.ftp.cmd.requests.impl.FTPRequestStor;
import com.ftp.cmd.requests.impl.FTPRequestType;
import com.ftp.states.State;
import com.ftp.utils.Context;

public class DataConnectedState extends State {

	@Override
	public void executeRequest(final Context context, final FTPRequest request) {
		super.executeRequest(context, request);
		request.executeState(context, this);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestList request) {
		final FTPResponse response = request.execute(context);
		context.getClient().sendResponse(response);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestCWD request) {
		final FTPResponse response = request.execute(context);
		context.getClient().sendResponse(response);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestPwd request) {
		handleRequest(context, request);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestEprt request) {
		final FTPResponse response = request.execute(context);
		context.getClient().sendResponse(response);
	}
	
	private void handleRequest(final Context context, final FTPRequest request) {
		final FTPResponse response = request.execute(context);
		
		context.getClient().sendResponse(response);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestMKD request) {
		final FTPResponse response = request.execute(context);
		context.getClient().sendResponse(response);
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
	protected String getName() {
		return "DataConnected";
	}

}
