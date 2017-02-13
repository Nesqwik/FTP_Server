package com.ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;

import com.ftp.cmd.FTPResponse;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.utils.Context;
import com.ftp.utils.Parser;

/**
 * La classe client permet de gérer les informations relatif à la session du client.
 * @author Jonathan Lecointe & Louis Guilbert
 */

public class Client implements Runnable {
	private final Socket cmdSocket;
	private final BufferedReader cmdReader;
	private final BufferedWriter cmdWriter;
	
	private Socket dataSocket;
	private ServerSocket dataSocketPassive;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private BufferedWriter dataWriter;
	
	private final Context context;
	
	private String dataAddr;
	private int dataPort;
	
	/**
	 * Constructeur du client
	 * @param client Le socket client permettant d'échanger avec le client.
	 * @throws IOException
	 */
	public Client(final Socket client) throws IOException {
		this.cmdSocket = client;
		
		context = new Context(this);
		cmdReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		cmdWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
	}
	
	/**
	 * Permet se connecter au serveur de data du client (en mode actif)
	 */
	public void connectDataSocket() {
		try {
			dataSocket = new Socket(dataAddr, dataPort);
			dataInputStream = new DataInputStream(dataSocket.getInputStream());
			dataOutputStream = new DataOutputStream(dataSocket.getOutputStream());
			dataWriter = new BufferedWriter(new OutputStreamWriter(dataSocket.getOutputStream()));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Permet d'ouvrir un socket data serveur pour que le client s'y connecte (en mode passif)
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void connectPassiveDataSocket() throws UnknownHostException, IOException {
		dataSocketPassive = new ServerSocket(0);
		dataPort = dataSocketPassive.getLocalPort();
		
		final Thread passiveConnectionThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					dataSocket = dataSocketPassive.accept();
					dataInputStream = new DataInputStream(dataSocket.getInputStream());
					dataOutputStream = new DataOutputStream(dataSocket.getOutputStream());
					dataWriter = new BufferedWriter(new OutputStreamWriter(dataSocket.getOutputStream()));
				} catch (final IOException e) {
					throw new RuntimeException(e);
				}
			}
		});
		
		context.setPassiveConnectionThread(passiveConnectionThread);
		passiveConnectionThread.start();
	}
	
	/**
	 * Ferme le socket client de données
	 */
	public void closeDataSocket() {
		try {
			dataSocket.close();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Permet d'envoyer une chaine de caractères au client via le dataSocket
	 * @param data
	 */
	public void sendStringData(final String data) {
		try {
			System.out.println(data + "\r\n");
			dataWriter.write(data + "\r\n");
			dataWriter.flush();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @return Renvoie le OutputStream permettant d'écrire des données en octets
	 */
	public DataOutputStream getDataOutputStream() {
		return dataOutputStream;
	}
	
	/**
	 * @return Renvoie le InputStream permettant de lire des données en octets
	 */
	public DataInputStream getDataInputStream() {
		return dataInputStream;
	}
	
	/**
	 * Implémentation du runnable
	 */
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
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Permet de renvoyer une réponse au client
	 * @param response
	 */
	public void sendResponse(final FTPResponse response) {
		try {
			cmdWriter.write(response.toString() + "\r\n");
			System.out.println(response.toString() + "\r\n");
			cmdWriter.flush();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Permet de fermer la connexion client/serveur
	 */
	public void quit() {
		try {
			cmdSocket.close();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @return Renvoie l'adresse du socket de données
	 */
	public String getDataAddr() {
		return dataAddr;
	}

	/**
	 * 
	 * @param dataAddr l'adresse du serveur de données
	 */
	public void setDataAddr(final String dataAddr) {
		this.dataAddr = dataAddr;
	}

	/**
	 *
	 * @return le port du serveur de données
	 */
	public int getDataPort() {
		return dataPort;
	}
	
	/**
	 * 
	 * @param dataPort le port du serveur de données
	 */
	public void setDataPort(final int dataPort) {
		this.dataPort = dataPort;
	}
}
