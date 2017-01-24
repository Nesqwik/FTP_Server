package com.ftp.cmd;

import com.ftp.Client;

public abstract class FTPRequest {
	
	protected String message;
	
	public FTPRequest() {
		this.message = null;
	}
	
	public abstract String getCode();
	public abstract FTPResponse execute(Client client);
}
