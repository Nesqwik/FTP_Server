package com.ftp.utils;

import java.net.InetSocketAddress;
import java.text.ParseException;

import com.ftp.cmd.Commands;
import com.ftp.cmd.requests.FTPRequest;

public class Parser {

	public static FTPRequest parseRequest(final String request) throws ParseException{
		
		final String[] words = request.split("\\s");
		
		if (words.length > 2 || words.length < 1) {
			throw new ParseException("Wrong number of parameters in command", 0);
		}
		
		final String code = words[0].trim();
		String message = "";
		if(words.length == 2){
			message = words[1].trim();
		}
		System.out.println(code + " : " + message);
		try {
			return Commands.valueOf(code).makeRequest(message);
		} catch (final IllegalArgumentException e) {
			throw new ParseException("Unknown command sent", 0);
		}
	}
	
	public static InetSocketAddress parseEPRT(final String requestMessage) {
		final String[] words = requestMessage.split("\\|");
		final String hostname = words[2];
		final int port = Integer.parseInt(words[3]);
		return new InetSocketAddress(hostname, port);
	}
}
