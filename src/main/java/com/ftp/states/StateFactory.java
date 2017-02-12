package com.ftp.states;

import com.ftp.states.impl.DataConnectedState;
import com.ftp.states.impl.InitialState;
import com.ftp.states.impl.LoggedInState;
import com.ftp.states.impl.RenamingState;
import com.ftp.states.impl.UserState;

public class StateFactory {
	
	private final static UserState userState = new UserState();
	private final static InitialState initialState = new InitialState();
	private final static LoggedInState loggedInState = new LoggedInState();
	private final static DataConnectedState dataConnectedState = new DataConnectedState();
	private final static RenamingState renamingState = new RenamingState();

	private StateFactory() {	}

	public static UserState getUserState() {
		return userState;
	}
	
	public static InitialState getInitialState() {
		return initialState;
	}
	
	public static LoggedInState getLoggedInState() {
		return loggedInState;
	}
	
	public static DataConnectedState getDataConnectedState() {
		return dataConnectedState;
	}
	
	public static RenamingState getRenamingState(final String fileToRenamePath) {
		renamingState.setFileToRename(fileToRenamePath);
		return renamingState;
	}
}
