package com.ftp.states.impl;

import java.net.InetSocketAddress;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestEprt;
import com.ftp.cmd.requests.impl.FTPRequestList;
import com.ftp.cmd.requests.impl.FTPRequestPwd;
import com.ftp.cmd.requests.impl.FTPRequestSyst;
import com.ftp.cmd.requests.impl.FTPRequestType;
import com.ftp.states.State;
import com.ftp.utils.Context;
import com.ftp.utils.Parser;

public class LoggedInState extends State {

	@Override
	public void executeRequest(Context context, FTPRequest request) {
		super.executeRequest(context, request);
		request.executeState(context, this);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestSyst request) {
		handleRequest(context, request);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestPwd request) {
		handleRequest(context, request);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestType request) {
		handleRequest(context, request);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestEprt request) {
		handleRequest(context, request);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestList request) {
		handleRequest(context, request);
	}
	
	// TODO : Ajouter command PORT + passer état DataConnected
	
	
	private void handleRequest(Context context, FTPRequest request) {
		FTPResponse response = request.execute(context);
		context.getClient().sendResponse(response);
	}
	
	@Override
	protected String getName() {
		return "LoggedIn";
	}

}
