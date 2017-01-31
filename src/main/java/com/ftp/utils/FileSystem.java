package com.ftp.utils;

import java.io.File;

public class FileSystem {
	private String rootDirectory;
	private File currentDirectory;
	
	public FileSystem(String rootDirectory) {
		this.rootDirectory = rootDirectory;
		
		this.currentDirectory = new File(rootDirectory);
	}
	
	public String[] list() {
		return currentDirectory.list();
	}
}
