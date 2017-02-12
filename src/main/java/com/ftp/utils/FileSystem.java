package com.ftp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class FileSystem {
	private String currentDirectory;
	private final String rootDirectory;
	
	public FileSystem(final String userDirectory) {
		this.rootDirectory = System.getProperty("user.dir") + userDirectory;		
		this.currentDirectory = "/";
	}
	
	private String getAbsoluteCurrentPath() {
		return this.rootDirectory + "/" + currentDirectory;
	}
	
	public String list() {
		try {
			final Process p = Runtime.getRuntime().exec("ls -al " + getAbsoluteCurrentPath());
			p.waitFor();
	
			final BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	
			String line = "";
			final StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine())!= null) {
				sb.append(line + "\r\n");
			}
			    
			return sb.toString();
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setCurrentDirectory(final String path) {
		// Replace for windows
		this.currentDirectory = Paths.get(path).normalize().toString().replace('\\', '/');
	}

	public void cwd(final String newDirectory) {		
		if (newDirectory.startsWith("/")) {
			System.out.println("Is absolute !");
			setCurrentDirectory(newDirectory);
		} else {
			System.out.println("Is Relatif !");
			if(currentDirectory.equals("/")){
				setCurrentDirectory(currentDirectory + newDirectory);
			} else {				
				setCurrentDirectory(currentDirectory + "/" + newDirectory);
			}
			
			// TODO : quand on fait .., .. reste dans le absolute path => c'est moche
		}
	}

	public String pwd() {
		// TODO: envoyer chemin relatif au rootDirectory du user
		return currentDirectory;
	}

	public boolean mkd(final String newFilePath) {
		final File newFile = new File(newFilePath);
		
		// TODO: verifier droits
		// si interdit -> 550 Permission Denied
		
		if (newFile.isAbsolute()) {
			return newFile.mkdirs();
		}
		
		return new File(getAbsoluteCurrentPath() + "/" + newFilePath).mkdirs();
	}
	
	public boolean rmd(final String filePath) {
		final File newFile = new File(filePath);
		
		// TODO: verifier droits
		// si interdit -> 550 Permission Denied
		
		if (newFile.isAbsolute()) {
			return newFile.delete();
		}
		
		return new File(getAbsoluteCurrentPath() + "/" + filePath).delete();
	}

	public void cdup() {
		cwd("/..");
	}
}
