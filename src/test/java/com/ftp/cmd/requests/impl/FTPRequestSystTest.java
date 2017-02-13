package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;

public class FTPRequestSystTest {
	@Test
	public void testSystOk() {
		final FTPRequestSyst cmd = new FTPRequestSyst();
		final Context context = Mockito.mock(Context.class);
		assertEquals(new FTPResponse(215, "UNIX").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestSyst cmd = new FTPRequestSyst();
		assertEquals(Commands.SYST, cmd.getCommand());
	}
}
