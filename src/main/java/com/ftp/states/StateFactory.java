package com.ftp.states;

import com.ftp.states.impl.DataConnectedState;
import com.ftp.states.impl.InitialState;
import com.ftp.states.impl.LoggedInState;
import com.ftp.states.impl.PassiveDataConnectedState;
import com.ftp.states.impl.RenamingState;
import com.ftp.states.impl.UserState;

/**
 * Permet de renvoyer l'instance de l'état demandé.
 * @author Jonathan Lecointe & Louis Guilbert
 *
 */
public class StateFactory {
	
	private final static UserState userState = new UserState();
	private final static InitialState initialState = new InitialState();
	private final static LoggedInState loggedInState = new LoggedInState();
	private final static DataConnectedState dataConnectedState = new DataConnectedState();
	private final static PassiveDataConnectedState passiveDataConnectedState = new PassiveDataConnectedState();

	private StateFactory() {	}

	/**
	 * @return L'instance de UserState.
	 */
	public static UserState getUserState() {
		return userState;
	}
	
	/**
	 * @return L'instance de InitialState.
	 */
	public static InitialState getInitialState() {
		return initialState;
	}
	
	/**
	 * @return L'instance de LoggedInState.
	 */
	public static LoggedInState getLoggedInState() {
		return loggedInState;
	}
	
	/**
	 * @return L'instance de DataConnectedState.
	 */
	public static DataConnectedState getDataConnectedState() {
		return dataConnectedState;
	}
	
	/**
	 * @return L'instance de PassiveDataConnectedState.
	 */
	public static PassiveDataConnectedState getPassiveDataConnectedState() {
		return passiveDataConnectedState;
	}
	
	/**
	 * @return une instance de RenamingState.
	 */
	public static RenamingState getRenamingState(final String fileToRenamePath) {
		/*  Ici, on renvoie un state différent à chaque fois car cet il a un état, et 
		 	cet état ne doit pas être partagé entre les différents clients.
		 */
		
		RenamingState state = new RenamingState();
		state.setFileToRename(fileToRenamePath);
		return state;
	}
}
