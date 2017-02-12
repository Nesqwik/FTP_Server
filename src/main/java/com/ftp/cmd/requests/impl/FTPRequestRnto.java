package com.ftp.cmd.requests.impl;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

public class FTPRequestRnto extends FTPRequest {
	
	protected String fileToRenamePath = null;
	
	public FTPRequestRnto(final String message) {
		super(message);
	}

	@Override
	public Commands getCommand() {
		return Commands.RNTO;
	}

	@Override
	public FTPResponse execute(final Context context) {
		System.out.println("rnto executing");
		if (context.getFileSystem().rename(fileToRenamePath, getMessage())){
			System.out.println("renaming ok");
			return new FTPResponse(250, "Requested file action okay, completed.");
		}
		System.out.println("renaming not ok");
		return new FTPResponse(550, "Requested action not taken.\nFile unavailable (e.g., file not found, no access).");
	}

	@Override
	public void executeState(final Context context, final State state) {
		state.concreteExecuteRequest(context, this);
	}
	
	public String getFileToRenamePath() {
		return fileToRenamePath;
	}

	public void setFileToRenamePath(final String fileToRenamePath) {
		this.fileToRenamePath = fileToRenamePath;
	}


}
