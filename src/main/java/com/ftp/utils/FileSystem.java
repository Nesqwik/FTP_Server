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
		this.rootDirectory = userDirectory;
		this.currentDirectory = "/";
		
		final File root = new File(rootDirectory);
		System.out.println(root);
		
		if (!root.exists()) {
			root.mkdirs();
		}
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
	
	public void writeFileToBuffer(final String fileName, final DataOutputStream dos) throws IOException{
		final File file = new File(rootDirectory + currentDirectory + "/" + fileName);
		final DataInputStream dis = new DataInputStream(new FileInputStream(file));
		transfertInputStream(dis, dos);
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
	
	public void writeFileToSystem(final String fileName, final DataInputStream dis) throws IOException {
		final File file = new File(rootDirectory + currentDirectory + "/" + fileName);
		final DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
		transfertInputStream(dis, dos);
	}

	public void cwd(final String newDirectory) throws IOException {
		final File file = makeFileFromPath(newDirectory);
		
			System.out.println(file.getCanonicalPath());
		if (file.exists() && file.getCanonicalPath().startsWith(rootDirectory)) {
			setCurrentDirectory(newDirectory);
		} else {
			throw new FileNotFoundException();
		}
	}

	public String pwd() {
		// TODO: envoyer chemin relatif au rootDirectory du user
		return "\"" + currentDirectory.replaceAll("\"", "\"\"") + "\"";
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

	public void cdup() throws IOException {
		//TODO: check if works as expected
		cwd("/..");
	}

	public boolean dele(final String fileName) {
		return makeFileFromPath(fileName).delete();
	}

	public boolean doesFileExist(final String filePath) {
		return makeFileFromPath(filePath).exists();
	}

	public boolean rename(final String fileToRenamePath, final String newFileName) {
		final File toRename = makeFileFromPath(fileToRenamePath);
		final File newName = makeFileFromPath(newFileName);
		
		return toRename.renameTo(newName);
	}
	
	protected File makeFileFromPath(final String filePath) {
		String actualFilePath = "";
		if (filePath.startsWith("/")) {
			System.out.println("Is absolute !");
			actualFilePath = filePath;
		} else {
			System.out.println("Is Relatif !");
			if (currentDirectory.equals("/")){
				actualFilePath = currentDirectory + filePath;
			} else {				
				actualFilePath = currentDirectory + "/" + filePath;
			}
		}
		return new File(rootDirectory + "/" + actualFilePath);
	}
}
