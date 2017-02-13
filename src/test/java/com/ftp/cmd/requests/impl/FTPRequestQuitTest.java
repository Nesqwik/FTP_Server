package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;

public class FTPRequestQuitTest {
	@Test
	public void testQuitOk() throws IOException {
		final FTPRequestQuit cmd = new FTPRequestQuit();
		final Context context = Mockito.mock(Context.class);
		
		assertEquals(new FTPResponse(221, "Goodbye.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestQuit cmd = new FTPRequestQuit();
		assertEquals(Commands.QUIT, cmd.getCommand());
	}
}
