package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;
import com.ftp.utils.FileSystem;

public class FTPRequestRnfrTest {
	@Test
	public void testRnfrOk() {
		final FTPRequestRnfr cmd = new FTPRequestRnfr("toto");
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		Mockito.doReturn(true).when(fs).doesFileExist(any(String.class));
		Mockito.when(context.getFileSystem()).thenReturn(fs);
		
		assertEquals(new FTPResponse(350, "Requested file action pending further information.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testRnfrFail() {
		final FTPRequestRnfr cmd = new FTPRequestRnfr("toto");
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		Mockito.doReturn(false).when(fs).doesFileExist(any(String.class));
		Mockito.when(context.getFileSystem()).thenReturn(fs);
		
		assertEquals(new FTPResponse(550, "Requested action not taken.\nFile unavailable (e.g., file not found, no access).").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestRnfr cmd = new FTPRequestRnfr("toto");
		assertEquals(Commands.RNFR, cmd.getCommand());
	}
}
