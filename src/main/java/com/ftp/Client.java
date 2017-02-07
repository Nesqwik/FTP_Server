package com.ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.ParseException;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.utils.Context;
import com.ftp.utils.Parser;

public class Client implements Runnable {
	private Socket cmdSocket;
	private BufferedReader cmdReader;
	private BufferedWriter cmdWriter;
	
	private Socket dataSocket;
	private BufferedReader dataReader;
	private BufferedWriter dataWriter;
	
	private Context context;
	
	private String dataAddr;
	private int dataPort;
	
	public Client(Socket client) throws IOException {
		this.cmdSocket = client;
		
		context = new Context(this);
		cmdReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		cmdWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
	}
	
	public void connectDataSocket() {
		try {
			dataSocket = new Socket(dataAddr, dataPort);
			dataReader = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
			dataWriter = new BufferedWriter(new OutputStreamWriter(dataSocket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeDataSocket() {
		try {
			dataSocket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void sendStringData(String data) {
		try {
			System.out.println(data + "\r\n");
			dataWriter.write(data + "\r\n");
			dataWriter.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void run() {		
		try {
			String request = "";
			sendResponse(new FTPResponse(220, "awaiting input"));
			System.out.println("waiting for requests");
			while((request = cmdReader.readLine()) != null) {
				try {
					FTPRequest ftpRequest = Parser.parseRequest(request);
					context.getCurrentState().executeRequest(context, ftpRequest);
				} catch (ParseException e) {
					sendResponse(FTPResponse.getCommandNotImplementedResponse());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendResponse(FTPResponse response) {
		try {
			cmdWriter.write(response.toString() + "\r\n");
			System.out.println(response.toString() + "\r\n");
			cmdWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void quit() {
		try {
			cmdSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getDataAddr() {
		return dataAddr;
	}

	public void setDataAddr(String dataAddr) {
		this.dataAddr = dataAddr;
	}

	public int getDataPort() {
		return dataPort;
	}

	public void setDataPort(int dataPort) {
		this.dataPort = dataPort;
	}
}
