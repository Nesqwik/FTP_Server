package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;

public class FTPRequestTypeTest {

	@Test
	public void testTypeOk() {
		final FTPRequestType cmd = new FTPRequestType();
		final Context context = Mockito.mock(Context.class);
		assertEquals(new FTPResponse(200, "ok").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestType cmd = new FTPRequestType();
		assertEquals(Commands.TYPE, cmd.getCommand());
	}
}
