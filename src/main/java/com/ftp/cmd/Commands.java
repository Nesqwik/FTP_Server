package com.ftp.cmd;

import com.ftp.cmd.requests.FTPRequest;
import com.ftp.cmd.requests.impl.FTPRequestCDUP;
import com.ftp.cmd.requests.impl.FTPRequestCWD;
import com.ftp.cmd.requests.impl.FTPRequestDele;
import com.ftp.cmd.requests.impl.FTPRequestEprt;
import com.ftp.cmd.requests.impl.FTPRequestList;
import com.ftp.cmd.requests.impl.FTPRequestMKD;
import com.ftp.cmd.requests.impl.FTPRequestPWD;
import com.ftp.cmd.requests.impl.FTPRequestPass;
import com.ftp.cmd.requests.impl.FTPRequestPort;
import com.ftp.cmd.requests.impl.FTPRequestQuit;
import com.ftp.cmd.requests.impl.FTPRequestRMD;
import com.ftp.cmd.requests.impl.FTPRequestRetr;
import com.ftp.cmd.requests.impl.FTPRequestRnfr;
import com.ftp.cmd.requests.impl.FTPRequestRnto;
import com.ftp.cmd.requests.impl.FTPRequestStor;
import com.ftp.cmd.requests.impl.FTPRequestSyst;
import com.ftp.cmd.requests.impl.FTPRequestType;
import com.ftp.cmd.requests.impl.FTPRequestUser;

public enum Commands {
	
	USER("USER") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestUser(message);
		}
	},
	PASS("PASS") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestPass(message);
		}
	},
	LIST("LIST") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestList();
		}
	},
	RETR("RETR") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestRetr(message);
		}
	},
	STOR("STOR") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestStor(message);
		}
	},
	QUIT("QUIT") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestQuit();
		}
	},
	SYST("SYST") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestSyst();
		}
	},
	PWD("PWD") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestPWD();
		}
	},
	TYPE("TYPE") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestType();
		}
	},
	EPRT("EPRT") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestEprt(message);
		}
	},
	CWD("CWD") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestCWD(message);
		}
	},
	MKD("MKD") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestMKD(message);
		}
	},
	RMD("RMD") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestRMD(message);
		}
	},
	CDUP("CDUP") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestCDUP();
		}
	},
	DELE("DELE") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestDele(message);
		}
	},
	PORT("PORT") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestPort(message);
		}
	},
	RNFR("RNFR") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestRnfr(message);
		}
	},
	RNTO("RNTO") {
		@Override
		public FTPRequest makeRequest(final String message) {
			return new FTPRequestRnto(message);
		}
	};
	
	public abstract FTPRequest makeRequest(String message);
	
	private String name;
	
	Commands(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
