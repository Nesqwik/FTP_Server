package com.ftp;

import java.text.ParseException;

import com.ftp.cmd.FTPRequest;
import com.ftp.cmd.requests.FTPRequestList;
import com.ftp.cmd.requests.FTPRequestPass;
import com.ftp.cmd.requests.FTPRequestQuit;
import com.ftp.cmd.requests.FTPRequestRetr;
import com.ftp.cmd.requests.FTPRequestStor;
import com.ftp.cmd.requests.FTPRequestUser;

public class Parser {

	public static FTPRequest parseRequest(String request) throws ParseException{
		
		String[] words = request.split("\\s");
		
		if (words.length > 2 || words.length < 1) {
			throw new ParseException("wrong number of parameters in command", 0);
		}
		
		String code = words[0].trim();
		String message = words[1].trim();
		
		switch (code) {
			case "USER" :
				return new FTPRequestUser(message);
			case "PASS" :
				return new FTPRequestPass(message);
			case "LIST" :
				return new FTPRequestList();
			case "RETR" :
				return new FTPRequestRetr(message);
			case "STOR" :
				return new FTPRequestStor(message);
			case "QUIT" :
				return new FTPRequestQuit();
			default :
				throw new ParseException("unknown command", 0);
		}
	}
}
