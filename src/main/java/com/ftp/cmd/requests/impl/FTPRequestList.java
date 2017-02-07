package com.ftp.cmd.requests.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.State;
import com.ftp.utils.Context;

public class FTPRequestList extends FTPRequest {

	@Override
	public Commands getCommand() {
		return Commands.LIST;
	}

	@Override
	public FTPResponse execute(Context context) {
		
	    try {
	    	Process p = Runtime.getRuntime().exec("ls -l /");
			p.waitFor();
			
			 BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

		    String line = "";
		    StringBuffer sb = new StringBuffer();
		    while ((line = reader.readLine())!= null) {
		    	sb.append(line + "\n");
		    }
		    context.getClient().sendStringData(sb.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		return new FTPResponse(226, "Closing data connection. Requested file action successful");
	}

	public void executeState(Context context, State state) {
		state.concreteExecuteRequest(context, this);
	}
}
