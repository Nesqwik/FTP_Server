package com.ftp.states;

import com.ftp.Client;
import com.ftp.cmd.FTPRequest;

public interface State {
	public void executeRequest(Client client, FTPRequest request);
}
