package com.ftp.states;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestCDUP;
import com.ftp.cmd.requests.impl.FTPRequestCWD;
import com.ftp.cmd.requests.impl.FTPRequestEprt;
import com.ftp.cmd.requests.impl.FTPRequestList;
import com.ftp.cmd.requests.impl.FTPRequestMKD;
import com.ftp.cmd.requests.impl.FTPRequestPass;
import com.ftp.cmd.requests.impl.FTPRequestPwd;
import com.ftp.cmd.requests.impl.FTPRequestQuit;
import com.ftp.cmd.requests.impl.FTPRequestRMD;
import com.ftp.cmd.requests.impl.FTPRequestRetr;
import com.ftp.cmd.requests.impl.FTPRequestStor;
import com.ftp.cmd.requests.impl.FTPRequestSyst;
import com.ftp.cmd.requests.impl.FTPRequestType;
import com.ftp.cmd.requests.impl.FTPRequestUser;
import com.ftp.utils.Context;

public abstract class State {
	public void executeRequest(Context context, FTPRequest request) {
		System.out.println("State '" + getName() + "' receiving request " + request.getCommand());
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestUser request) {
		sendNotImplementedResponse(context);
	}
	public void concreteExecuteRequest(Context context, FTPRequestList request) {
		sendNotImplementedResponse(context);
	}
	public void concreteExecuteRequest(Context context, FTPRequestPass request) {
		sendNotImplementedResponse(context);
	}
	public void concreteExecuteRequest(Context context, FTPRequestQuit request) {
		FTPResponse response = request.execute(context);
		context.getClient().sendResponse(response);
		context.getClient().quit();
	}
	public void concreteExecuteRequest(Context context, FTPRequestRetr request) {
		sendNotImplementedResponse(context);
	}
	public void concreteExecuteRequest(Context context, FTPRequestStor request) {
		sendNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestSyst request) {
		sendNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestPwd request) {
		sendNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestType request) {
		sendNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestEprt request) {
		sendNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestCWD request) {
		sendNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestMKD request) {
		sendNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestRMD request) {
		sendNotImplementedResponse(context);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestCDUP request) {
		sendNotImplementedResponse(context);
	}
	
	protected void sendNotImplementedResponse(Context context) {
        //TODO: 530 Not logged in.
		context.getClient().sendResponse(FTPResponse.getCommandNotImplementedResponse());
	}
	
	protected abstract String getName();
}
