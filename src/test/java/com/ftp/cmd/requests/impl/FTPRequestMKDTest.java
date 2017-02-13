package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;
import com.ftp.utils.FileSystem;

public class FTPRequestMKDTest {
	@Test
	public void testMKDOk() {
		final FTPRequestMKD cmd = new FTPRequestMKD("toto");
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		Mockito.doReturn(true).when(fs).mkd(any(String.class));
		Mockito.when(context.getFileSystem()).thenReturn(fs);
		
		assertEquals(new FTPResponse(257, "\"toto\" directory created").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testMKDFail() {
		final FTPRequestMKD cmd = new FTPRequestMKD("toto");
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		Mockito.doReturn(false).when(fs).mkd(any(String.class));
		Mockito.when(context.getFileSystem()).thenReturn(fs);	
		
		assertEquals(new FTPResponse(550, "Requested action not taken.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestMKD cmd = new FTPRequestMKD("toto");
		assertEquals(Commands.MKD, cmd.getCommand());
	}
}
