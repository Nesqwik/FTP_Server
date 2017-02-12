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
	/**
	 * Parses eprt request
	 * @param requestMessage should be formatted as follows :
	 * 	EPRT : |2|client_address|client_data_port|
	 * @return
	 * @throws ParseException
	 */
	public static InetSocketAddress parseEPRT(final String requestMessage) throws ParseException {
		try {
			final String[] words = requestMessage.split("\\|");
	
			if (words.length != 4) {
				throw new Exception();
			}
			final String hostname = words[2];
			final int port = Integer.parseInt(words[3]);
			
			return new InetSocketAddress(hostname, port);
		} catch (final Exception e) {
			// thrown if the request is badly formatted or if the port section is not a number
			throw new ParseException("The PORT message is badly formatted", 0);
		}
	}
	
	public static InetSocketAddress parsePORT(final String requestMessage) throws ParseException {
		try {
			final String[] words = requestMessage.split(",");
			if (words.length != 6) {
				throw new Exception();
			}
			
			final String hostname = words[0] + "." + words[1] + "." + words[2] + "." + words[3];
			final int port = Integer.parseInt(words[4]) * 256 + Integer.parseInt(words[5]);

			return new InetSocketAddress(hostname, port);
		} catch (final Exception e) {
			// thrown if the request is badly formatted or if the port section is not a number
			throw new ParseException("The PORT message is badly formatted", 0);
		}
	}
}
