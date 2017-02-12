package com.ftp.cmd.requests;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

public abstract class FTPRequest {
	
	protected String message;
	
	public FTPRequest() {
		this.message = null;
	}
	
	public FTPRequest(String message) {
		this.message = message;
	}
	
	public abstract Commands getCommand();
	public abstract FTPResponse execute(Context context);
	
	public abstract void executeState(Context context, State state);

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
