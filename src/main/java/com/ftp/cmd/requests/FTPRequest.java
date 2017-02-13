package com.ftp.cmd.requests;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.states.api.State;
import com.ftp.utils.Context;

/**
 * Classe abstraite permettant l'interfaçage des requêtes FTP envoyées par le client.
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
	 * Execute la commande relative à la requête et renvoie une réponse associée.
	 * @param context
	 * @return
	 */
	public abstract FTPResponse execute(Context context);
	
	/**
	 * Exécute l'état passé en paramètre.
	 * L'intérêt de cette méthode est que puisque l'on appelle cette méthode depuis la
	 * FTPRequest, l'état connaîtra le type concret de la requête, et pourra donc appeller
	 * la méthode associée au type concret.
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
