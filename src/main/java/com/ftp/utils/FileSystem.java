package com.ftp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileSystem {
	private String rootDirectory;
	private File currentDirectory;
	
	public FileSystem(String rootDirectory) {
		this.rootDirectory = rootDirectory;
		
		this.currentDirectory = new File(rootDirectory);
	}
	
	public String list() {
		try {
			Process p = Runtime.getRuntime().exec("ls -al " + currentDirectory.getAbsolutePath());
			p.waitFor();
	
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	
			String line = "";
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine())!= null) {
				sb.append(line + "\r\n");
			}
			    
			return sb.toString();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void cwd(String newDirectory) {
		File newDir = new File(newDirectory);
		
		if (newDir.isAbsolute()) {
			currentDirectory = newDir;
		} else {
			String newPath = currentDirectory.getAbsolutePath() + "/" + newDirectory;
			
			// TODO : quand on fait .., .. reste dans le absolute path => c'est moche
			currentDirectory = new File(newPath);
		}
	}

	public String pwd() {
		// TODO: envoyer chemin relatif au rootDirectory du user
		return currentDirectory.getAbsolutePath();
	}

	public boolean mkd(String newFilePath) {
		File newFile = new File(newFilePath);
		
		// TODO: verifier droits
		// si interdit -> 550 Permission Denied
		
		if (newFile.isAbsolute()) {
			return newFile.mkdirs();
		}
		
		return new File(currentDirectory.getAbsolutePath() + "/" + newFilePath).mkdirs();
	}
	
	public boolean rmd(String filePath) {
		File newFile = new File(filePath);
		
		// TODO: verifier droits
		// si interdit -> 550 Permission Denied
		
		if (newFile.isAbsolute()) {
			return newFile.delete();
		}
		
		return new File(currentDirectory.getAbsolutePath() + "/" + filePath).delete();
	}

	public void cdup() {
		cwd("..");
	}
}
