package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;
import com.ftp.utils.FileSystem;

public class FTPRequestDeleTest {
	@Test
	public void testDeleOk() {
		final FTPRequestDele cmd = new FTPRequestDele("toto");
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		Mockito.doReturn(true).when(fs).dele(any(String.class));
		Mockito.when(context.getFileSystem()).thenReturn(fs);
		
		assertEquals(new FTPResponse(250, "Requested file action okay, completed.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testDeleFail() {
		final FTPRequestDele cmd = new FTPRequestDele("toto");
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		Mockito.doReturn(false).when(fs).dele(any(String.class));
		Mockito.when(context.getFileSystem()).thenReturn(fs);	
		
		assertEquals(new FTPResponse(550, "Requested action not taken.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestDele cmd = new FTPRequestDele("toto");
		assertEquals(Commands.DELE, cmd.getCommand());
	}
}
