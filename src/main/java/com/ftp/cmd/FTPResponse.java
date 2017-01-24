package com.ftp.cmd;

public class FTPResponse {
	private int code;
	private String message;
	
	public FTPResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String toString() {
		return code + " " + message;
	}
}
