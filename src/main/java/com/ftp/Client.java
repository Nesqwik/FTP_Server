package com.ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.ParseException;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.utils.Context;
import com.ftp.utils.Parser;

public class Client implements Runnable {
	private Socket client;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private Context context;
	
	public Client(Socket client) throws IOException {
		this.client = client;
		
		context = new Context(this);
		reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
	}
	
	public void run() {		
		try {
			String request = "";
			System.out.println("waiting for requests");
			while((request = reader.readLine()) != null) {
				try {
					FTPRequest ftpRequest = Parser.parseRequest(request);
					context.getCurrentState().executeRequest(context, ftpRequest);
				} catch (ParseException e) {
					// TODO: return bad request
					System.out.println(e.getMessage());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendResponse(FTPResponse response) {
		try {
			writer.write(response.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void quit() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
