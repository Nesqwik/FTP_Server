package com.ftp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;

public class Server {
	
    public static void main(String[] args) throws IOException, ParseException {
    	Server s = new Server();
    	s.start();
    }
    
    public void start() throws ParseException {
    	ServerSocket cmdSocket;
		try {
			cmdSocket = new ServerSocket(2020);
	    	
			while(true) {
				System.out.println("Accepting...");
				Socket clientSocket = cmdSocket.accept();
				System.out.println("Client accepted !");
				
				new Thread(new Client(clientSocket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
