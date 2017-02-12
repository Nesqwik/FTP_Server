package com.ftp.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	
	public void writeFileToBuffer(final String fileName, final DataOutputStream dos) {
		try {
			final File file = new File(rootDirectory + currentDirectory + "/" + fileName);
			final DataInputStream dis = new DataInputStream(new FileInputStream(file));
			transfertInputStream(dis, dos);
			dis.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void transfertInputStream(final DataInputStream dis, final DataOutputStream dos) {
		try {
			final byte[] buffer = new byte[2048];
			int nbByte = 0;
			while((nbByte = dis.read(buffer)) != -1) {
				dos.write(buffer, 0, nbByte);
			}
			dos.flush();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeFileToSystem(final String fileName, final DataInputStream dis) {
		try {
			final File file = new File(rootDirectory + currentDirectory + "/" + fileName);
			final DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
			transfertInputStream(dis, dos);
			dos.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cwd(String newDirectory) throws FileNotFoundException {
		if (newDirectory.startsWith("/")) {
			System.out.println("Is absolute !");
		} else {
			System.out.println("Is Relatif !");
			if(currentDirectory.equals("/")){
				newDirectory = currentDirectory + newDirectory;
			} else {				
				newDirectory = currentDirectory + "/" + newDirectory;
			}
		}
		final File file = new File(rootDirectory + "/" + newDirectory);
		if(file.exists()) {
			setCurrentDirectory(newDirectory);
		} else {
			throw new FileNotFoundException();
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

	public void cdup() throws FileNotFoundException {
		cwd("/..");
	}

	public boolean dele(final String fileName) {
		final File file = new File(rootDirectory + currentDirectory + "/" + fileName);
		return file.delete();
	}
}
