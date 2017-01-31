package com.ftp.utils;

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
			System.out.println(code + " : " + message);
			throw new ParseException("Unknown command sent", 0);
		}
	}
}
