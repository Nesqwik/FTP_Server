package com.ftp.states;

import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestList;
import com.ftp.cmd.requests.impl.FTPRequestPass;
import com.ftp.cmd.requests.impl.FTPRequestQuit;
import com.ftp.cmd.requests.impl.FTPRequestRetr;
import com.ftp.cmd.requests.impl.FTPRequestStor;
import com.ftp.cmd.requests.impl.FTPRequestUser;
import com.ftp.utils.Context;

public abstract class State {
	public void executeRequest(Context context, FTPRequest request) {
		System.out.println("State '" + getName() + "' receiving request " + request.getCommand());
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestUser request) {
		sendBadRequest();
	}
	public void concreteExecuteRequest(Context context, FTPRequestList request) {
		sendBadRequest();
	}
	public void concreteExecuteRequest(Context context, FTPRequestPass request) {
		sendBadRequest();
	}
	public void concreteExecuteRequest(Context context, FTPRequestQuit request) {
		sendBadRequest();
	}
	public void concreteExecuteRequest(Context context, FTPRequestRetr request) {
		sendBadRequest();
	}
	public void concreteExecuteRequest(Context context, FTPRequestStor request) {
		sendBadRequest();
	}
	
	protected void sendBadRequest() {
		//TODO: handle when bad request
		System.out.println("this state can't accept this request");
	}
	
	protected abstract String getName();
}
