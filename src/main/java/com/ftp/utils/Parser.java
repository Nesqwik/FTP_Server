package com.ftp.utils;

import java.net.InetSocketAddress;
import java.text.ParseException;

import com.ftp.cmd.Commands;
import com.ftp.cmd.requests.FTPRequest;

public class Parser {

	public static FTPRequest parseRequest(String request) throws ParseException{
		
		String[] words = request.split("\\s");
		
		if (words.length > 2 || words.length < 1) {
			throw new ParseException("Wrong number of parameters in command", 0);
		}
		
		String code = words[0].trim();
		String message = "";
		if(words.length == 2){
			message = words[1].trim();
		}
		
		try {
			System.out.println(code + " : " + message);
			return Commands.valueOf(code).makeRequest(message);
		} catch (IllegalArgumentException e) {
			throw new ParseException("Unknown command sent", 0);
		}
	}
	
	public static InetSocketAddress parseEPRT(String requestMessage) {
		String[] words = requestMessage.split("\\|");
		String hostname = words[2];
		int port = Integer.parseInt(words[3]);
		return new InetSocketAddress(hostname, port);
	}
}
