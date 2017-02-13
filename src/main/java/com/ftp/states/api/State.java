package com.ftp.states.api;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestCDUP;
import com.ftp.cmd.requests.impl.FTPRequestCWD;
import com.ftp.cmd.requests.impl.FTPRequestDele;
import com.ftp.cmd.requests.impl.FTPRequestEprt;
import com.ftp.cmd.requests.impl.FTPRequestEpsv;
import com.ftp.cmd.requests.impl.FTPRequestList;
import com.ftp.cmd.requests.impl.FTPRequestMKD;
import com.ftp.cmd.requests.impl.FTPRequestPASV;
import com.ftp.cmd.requests.impl.FTPRequestPWD;
import com.ftp.cmd.requests.impl.FTPRequestPass;
import com.ftp.cmd.requests.impl.FTPRequestPort;
import com.ftp.cmd.requests.impl.FTPRequestQuit;
import com.ftp.cmd.requests.impl.FTPRequestRMD;
import com.ftp.cmd.requests.impl.FTPRequestRetr;
import com.ftp.cmd.requests.impl.FTPRequestRnfr;
import com.ftp.cmd.requests.impl.FTPRequestRnto;
import com.ftp.cmd.requests.impl.FTPRequestStor;
import com.ftp.cmd.requests.impl.FTPRequestSyst;
import com.ftp.cmd.requests.impl.FTPRequestType;
import com.ftp.cmd.requests.impl.FTPRequestUser;
import com.ftp.utils.Context;

public abstract class State {
	public void executeRequest(final Context context, final FTPRequest request) {
		System.out.println("State '" + getName() + "' receiving request " + request.getCommand());
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestUser request) {
		onNotImplementedResponse(context);
	}
	public void concreteExecuteRequest(final Context context, final FTPRequestList request) {
		onNotImplementedResponse(context);
	}
	public void concreteExecuteRequest(final Context context, final FTPRequestPass request) {
		onNotImplementedResponse(context);
	}
	public void concreteExecuteRequest(final Context context, final FTPRequestQuit request) {
		final FTPResponse response = request.execute(context);
		context.getClient().sendResponse(response);
		context.getClient().quit();
	}
	public void concreteExecuteRequest(final Context context, final FTPRequestRetr request) {
		onNotImplementedResponse(context);
	}
	public void concreteExecuteRequest(final Context context, final FTPRequestStor request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestSyst request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestPWD request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestType request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestEprt request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestCWD request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestMKD request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestRMD request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestCDUP request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestDele request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestPort request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestRnfr request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestRnto request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestPASV request) {
		onNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(final Context context, final FTPRequestEpsv request) {
		onNotImplementedResponse(context);
	}

	protected void onNotImplementedResponse(final Context context) {
        //TODO: 530 Not logged in.
		context.getClient().sendResponse(FTPResponse.getCommandNotImplementedResponse());
	}
	
	protected void handleRequest(final Context context, final FTPRequest request) {
		final FTPResponse response = request.execute(context);
		context.getClient().sendResponse(response);
	}
	
	public abstract String getName();
}
