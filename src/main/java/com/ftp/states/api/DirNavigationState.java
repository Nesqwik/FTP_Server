package com.ftp.states.api;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.impl.FTPRequestCDUP;
import com.ftp.cmd.requests.impl.FTPRequestCWD;
import com.ftp.cmd.requests.impl.FTPRequestMKD;
import com.ftp.cmd.requests.impl.FTPRequestPWD;
import com.ftp.cmd.requests.impl.FTPRequestRMD;
import com.ftp.utils.Context;

public abstract class DirNavigationState extends State {

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
	public void concreteExecuteRequest(final Context context, final FTPRequestCWD request) {
		final FTPResponse response = request.execute(context);
		context.getClient().sendResponse(response);
	}
	
	@Override
	public void concreteExecuteRequest(final Context context, final FTPRequestPWD request) {
		handleRequest(context, request);
	}
}
