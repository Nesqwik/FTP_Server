package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

public class FTPRequestList extends FTPRequest {

	@Override
	public Commands getCommand() {
		return Commands.LIST;
	}

	@Override
	public FTPResponse execute(Context context) {
		context.getClient().connectDataSocket();
	    context.getClient().sendResponse(new FTPResponse(150, "Here comes the directory listing."));
		
	    
	    context.getClient().sendStringData(context.getFileSystem().list());
	    
	    
	    context.getClient().closeDataSocket();
	    
		return new FTPResponse(226, "Directory send OK.");
	}

	public void executeState(Context context, State state) {
		state.concreteExecuteRequest(context, this);
	}
}
