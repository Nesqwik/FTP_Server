package com.ftp.states.impl;

import com.ftp.cmd.requests.FTPRequest;
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

	//TODO: handle requests

}
