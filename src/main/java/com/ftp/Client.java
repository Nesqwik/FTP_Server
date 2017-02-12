package com.ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
	private final Socket cmdSocket;
	private final BufferedReader cmdReader;
	private final BufferedWriter cmdWriter;
	
	private Socket dataSocket;
	private BufferedReader dataReader;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private BufferedWriter dataWriter;
	
	private final Context context;
	
	private String dataAddr;
	private int dataPort;
	
	public Client(final Socket client) throws IOException {
		this.cmdSocket = client;
		
		context = new Context(this);
		cmdReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		cmdWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
	}
	
	public void connectDataSocket() {
		try {
			dataSocket = new Socket(dataAddr, dataPort);
			dataInputStream = new DataInputStream(dataSocket.getInputStream());
			dataOutputStream = new DataOutputStream(dataSocket.getOutputStream());
			dataReader = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
			dataWriter = new BufferedWriter(new OutputStreamWriter(dataSocket.getOutputStream()));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeDataSocket() {
		try {
			dataSocket.close();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void sendStringData(final String data) {
		try {
			System.out.println(data + "\r\n");
			dataWriter.write(data + "\r\n");
			dataWriter.flush();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public DataOutputStream getDataOutputStream() {
		return dataOutputStream;
	}
	
	public DataInputStream getDataInputStream() {
		return dataInputStream;
	}
	
	@Override
	public void run() {		
		try {
			String request = "";
			sendResponse(new FTPResponse(220, "awaiting input"));
			System.out.println("waiting for requests");
			while(!cmdSocket.isClosed() && (request = cmdReader.readLine()) != null) {
				try {
					final FTPRequest ftpRequest = Parser.parseRequest(request);
					context.getCurrentState().executeRequest(context, ftpRequest);
				} catch (final ParseException e) {
					sendResponse(FTPResponse.getCommandNotImplementedResponse());
				}
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendResponse(final FTPResponse response) {
		try {
			cmdWriter.write(response.toString() + "\r\n");
			System.out.println(response.toString() + "\r\n");
			cmdWriter.flush();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	public void quit() {
		try {
			cmdSocket.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getDataAddr() {
		return dataAddr;
	}

	public void setDataAddr(final String dataAddr) {
		this.dataAddr = dataAddr;
	}

	public int getDataPort() {
		return dataPort;
	}

	public void setDataPort(final int dataPort) {
		this.dataPort = dataPort;
	}
}
