package com.ftp.cmd.requests;

import com.ftp.Client;
import com.ftp.cmd.FTPRequest;
import com.ftp.cmd.FTPResponse;

public class FTPRequestList extends FTPRequest {

	@Override
	public String getCode() {
		return "LIST";
	}

	@Override
	public FTPResponse execute(Client client) {
		return new FTPResponse(200, "ok");
	}

}
