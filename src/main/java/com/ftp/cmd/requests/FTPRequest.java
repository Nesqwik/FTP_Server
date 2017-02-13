package com.ftp.cmd.requests;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

/**
 * Classe abstraite permettant l'interfaçage des requêtes FTP envoyé par le client.
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public abstract class FTPRequest {
	
	protected String message;
	
	/** 
	 * Constructeur par défaut
	 */
	public FTPRequest() {
		this.message = null;
	}
	
	/**
	 * Constructeur alternatif
	 * @param message message envoyé par le client
	 */
	public FTPRequest(String message) {
		this.message = message;
	}
	
	/**
	 * 
	 * @return l'enum de la commande
	 */
	public abstract Commands getCommand();
	
	/**
	 * Execute la commande relatif à la requête et renvoie une réponse associée.
	 * @param context
	 * @return
	 */
	public abstract FTPResponse execute(Context context);
	
	/**
	 * 
	 * @param context
	 * @param state
	 */
	public abstract void executeState(Context context, State state);

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
