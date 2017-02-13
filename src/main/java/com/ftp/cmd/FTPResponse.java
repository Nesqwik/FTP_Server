package com.ftp.cmd;
/**
 * Classe contenant une réponse destinée au client
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class FTPResponse {
	private int code;
	private String message;
	
	/** 
	 * Constructeur.
	 * @param code code de succès/erreur... (RFC FTP)
	 * @param message message de succès/erreur... (RFC FTP)
	 */
	public FTPResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * 
	 * @return code de succès/erreur... (RFC FTP)
	 */
	public int getCode() {
		return this.code;
	}
	
	/**
	 * Permet l'affichage du code d'erreur
	 */
	public String toString() {
		return code + " " + message;
	}
	
	/**
	 * Création de la réponse "502 non implémenté"
	 * @return la réponse 502
	 */
	public static FTPResponse getCommandNotImplementedResponse() {
		return new FTPResponse(502, "Command not implemented");
	}
}
