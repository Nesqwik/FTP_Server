package com.ftp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;

import com.ftp.utils.Parser;

public class Main {
	
    public static void main(final String[] args) throws IOException, ParseException {
    	Parser.parseArgs(args);
    	final Main s = new Main();
    	s.start();
    }
    
    public void start() throws ParseException {
    	ServerSocket cmdSocket;
		try {
			cmdSocket = new ServerSocket(2020);
	    	
			while(true) {
				System.out.println("Accepting...");
				final Socket clientSocket = cmdSocket.accept();
				System.out.println("Client accepted !");
				
				new Thread(new Client(clientSocket)).start();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
    }
    
}
