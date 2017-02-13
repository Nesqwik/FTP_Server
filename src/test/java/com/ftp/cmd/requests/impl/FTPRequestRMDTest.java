package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;
import com.ftp.utils.FileSystem;

public class FTPRequestRMDTest {
	@Test
	public void testRMDOk() {
		final FTPRequestRMD cmd = new FTPRequestRMD("toto");
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		Mockito.doReturn(true).when(fs).rmd(any(String.class));
		Mockito.when(context.getFileSystem()).thenReturn(fs);
		
		assertEquals(new FTPResponse(250, "Requested file action okay, completed.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testRMDFail() {
		final FTPRequestRMD cmd = new FTPRequestRMD("toto");
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		Mockito.doReturn(false).when(fs).rmd(any(String.class));
		Mockito.when(context.getFileSystem()).thenReturn(fs);
		
		assertEquals(new FTPResponse(550, "Requested action not taken.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestRMD cmd = new FTPRequestRMD("toto");
		assertEquals(Commands.RMD, cmd.getCommand());
	}
}
