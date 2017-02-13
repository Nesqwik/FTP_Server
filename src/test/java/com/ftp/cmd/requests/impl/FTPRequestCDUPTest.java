package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;
import com.ftp.utils.FileSystem;

public class FTPRequestCDUPTest {
	@Test
	public void testCDUPOk() throws IOException {
		final FTPRequestCDUP cmd = new FTPRequestCDUP();
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		Mockito.doNothing().when(fs).cdup();
		Mockito.doReturn("toto").when(fs).pwd();
		Mockito.when(context.getFileSystem()).thenReturn(fs);
		
		assertEquals(new FTPResponse(200, "directory changed to toto").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testCDUPFail() throws IOException {
		final FTPRequestCDUP cmd = new FTPRequestCDUP();
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		Mockito.doThrow(IOException.class).when(fs).cdup();
		Mockito.when(context.getFileSystem()).thenReturn(fs);	
		
		assertEquals(new FTPResponse(550, "Requested action not taken.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestCDUP cmd = new FTPRequestCDUP();
		assertEquals(Commands.CDUP, cmd.getCommand());
	}
}
