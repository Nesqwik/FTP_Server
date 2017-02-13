package com.ftp.utils;

import java.net.InetSocketAddress;
import java.text.ParseException;

import com.ftp.cmd.Commands;
import com.ftp.cmd.requests.FTPRequest;
import com.ftp.database.Database;

/**
 * Classe permettant de parser les requêtes du client.
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class Parser {

	/**
	 * Parse une requête du client en code + message
	 * @param request la requête à parser.
	 * @return un objet FTPRequest contenant le code + message.
	 * @throws ParseException
	 */
	public static FTPRequest parseRequest(final String request) throws ParseException {
		
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
	 * Parse les requête EPRT
	 * @param requestMessage devrait être formaté comme suit
	 * 	EPRT : |2|client_address|client_data_port|
	 * @return la connexion crée à partir des données du message
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
			// Exception lancée si la requête est mal formatée ou si le port n'est pas un nombre
			throw new ParseException("The PORT message is badly formatted", 0);
		}
	}
	
	/**
	 * Permet de parser une requête PORT
	 * @param requestMessage le message à parser
	 * @return un objet InetSocketAddress contenant l'adresse IP et le port
	 * @throws ParseException
	 */
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
			// Exception lancée si la requête est mal formatée ou si le port n'est pas un nombre
			throw new ParseException("The PORT message is badly formatted", 0);
		}
	}
	
	/**
	 * Permet de créer le retour pour le client à la commande PASV.
	 * @param hostname le hostname
	 * @param port le port du serveur
	 * @return la chaine formatté selon les RFC FTP.
	 */
	public static String makePASVResponse(final String hostname, final int port) {
		return "=" + hostname.replaceAll(".", ",") + port / 256 + "," + port % 256;
	}
	
	/**
	 * Parse les arguments passés au serveur au lancement
	 * @param args les arguments du programme
	 */
	public static void parseArgs(final String[] args) {
		if (args.length >= 2 && args[0].equals("-rd")) {
			Database.getInstance().setDirectoriesRoot(args[1]);
		}
		
	}
}
