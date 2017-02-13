package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;
import com.ftp.utils.FileSystem;

public class FTPRequestPWDTest {
	@Test
	public void testPWDOk() throws IOException {
		final FTPRequestPWD cmd = new FTPRequestPWD();
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		
		Mockito.when(context.getFileSystem()).thenReturn(fs);
		Mockito.doReturn("/path/to/wd").when(fs).pwd();
		
		assertEquals(new FTPResponse(257, "/path/to/wd").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestPWD cmd = new FTPRequestPWD();
		assertEquals(Commands.PWD, cmd.getCommand());
	}
}
