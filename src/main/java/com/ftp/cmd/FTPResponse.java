package com.ftp.cmd;

public class FTPResponse {
	private int code;
	private String message;
	
	public FTPResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String toString() {
		return code + " " + message;
	}
	
	public static FTPResponse getCommandNotImplementedResponse() {
		return new FTPResponse(502, "Command not implemented");
	}
}
