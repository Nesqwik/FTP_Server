package com.ftp.states.impl;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestList;
import com.ftp.cmd.requests.impl.FTPRequestUser;
import com.ftp.states.State;
import com.ftp.utils.Context;

public class DataConnectedState extends State {

	@Override
	public void executeRequest(Context context, FTPRequest request) {
		super.executeRequest(context, request);
		request.executeState(context, this);
	}
	
	@Override
	public void concreteExecuteRequest(Context context, FTPRequestList request) {
		FTPResponse response = request.execute(context);
		
		//context.setCurrentState(new UserState());
		
		context.getClient().sendResponse(response);
	}
	
	@Override
	protected String getName() {
		return "DataConnected";
	}

}
