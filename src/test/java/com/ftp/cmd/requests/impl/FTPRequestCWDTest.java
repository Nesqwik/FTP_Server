package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;
import com.ftp.utils.FileSystem;

public class FTPRequestCWDTest {
	@Test
	public void testCWDOk() throws IOException {
		final FTPRequestCWD cmd = new FTPRequestCWD("toto");
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		Mockito.doNothing().when(fs).cwd(any(String.class));
		Mockito.when(context.getFileSystem()).thenReturn(fs);
		
		assertEquals(new FTPResponse(250, "Directory successfully changed.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testCWDFail() throws IOException {
		final FTPRequestCWD cmd = new FTPRequestCWD("toto");
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		Mockito.doThrow(IOException.class).when(fs).cwd(any(String.class));
		Mockito.when(context.getFileSystem()).thenReturn(fs);	
		
		assertEquals(new FTPResponse(550, "Requested action not taken.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestCWD cmd = new FTPRequestCWD("toto");
		assertEquals(Commands.CWD, cmd.getCommand());
	}
}
