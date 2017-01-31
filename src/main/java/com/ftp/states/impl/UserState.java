package com.ftp.states.impl;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestPass;
import com.ftp.cmd.requests.impl.FTPRequestQuit;
import com.ftp.states.State;
import com.ftp.utils.Context;

public class UserState extends State {

	@Override
	public void executeRequest(Context context, FTPRequest request) {
		super.executeRequest(context, request);
		request.executeState(context, this);
	}

	@Override
	protected String getName() {
		return "user";
	}

	public void concreteExecuteRequest(Context context, FTPRequestPass request) {
		FTPResponse response = request.execute(context);
		
		if(response.getCode() == 230)
			context.setCurrentState(new LoggedInState());
		
		context.getClient().sendResponse(response);
	}
	
	public void concreteExecuteRequest(Context context, FTPRequestQuit request) {
		request.execute(context);
	}

}
