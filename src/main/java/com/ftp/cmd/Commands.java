package com.ftp.cmd;

import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestList;
import com.ftp.cmd.requests.impl.FTPRequestPass;
import com.ftp.cmd.requests.impl.FTPRequestQuit;
import com.ftp.cmd.requests.impl.FTPRequestRetr;
import com.ftp.cmd.requests.impl.FTPRequestStor;
import com.ftp.cmd.requests.impl.FTPRequestSyst;
import com.ftp.cmd.requests.impl.FTPRequestUser;

public enum Commands {
	
	USER("USER") {
		@Override
		public FTPRequest makeRequest(String message) {
			return new FTPRequestUser(message);
		}
	},
	PASS("PASS") {
		@Override
		public FTPRequest makeRequest(String message) {
			return new FTPRequestPass(message);
		}
	},
	LIST("LIST") {
		@Override
		public FTPRequest makeRequest(String message) {
			return new FTPRequestList();
		}
	},
	RETR("RETR") {
		@Override
		public FTPRequest makeRequest(String message) {
			return new FTPRequestRetr(message);
		}
	},
	STOR("STOR") {
		@Override
		public FTPRequest makeRequest(String message) {
			return new FTPRequestStor(message);
		}
	},
	QUIT("QUIT") {
		@Override
		public FTPRequest makeRequest(String message) {
			return new FTPRequestQuit();
		}
	},
	SYST("SYST") {
		@Override
		public FTPRequest makeRequest(String message) {
			return new FTPRequestSyst();
		}
	};
	
	public abstract FTPRequest makeRequest(String message);
	
	private String name;
	
	Commands(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
