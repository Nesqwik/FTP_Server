package com.ftp.cmd.requests;

import com.ftp.Client;
import com.ftp.cmd.FTPRequest;
import com.ftp.cmd.FTPResponse;

public class FTPRequestStor extends FTPRequest {
	
	public FTPRequestStor(String message) {
		this.message = message;
	}

	@Override
	public String getCode() {
		return "STOR";
	}

	@Override
	public FTPResponse execute(Client client) {
		return new FTPResponse(200, "ok");
	}

}
