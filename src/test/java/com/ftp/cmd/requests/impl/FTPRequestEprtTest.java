package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.Client;
import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;

public class FTPRequestEprtTest {
	@Test
	public void testEprtOk() {
		final FTPRequestEprt cmd = new FTPRequestEprt("|2|::0|12345|");
		final Context context = Mockito.mock(Context.class);
		final Client client = Mockito.mock(Client.class);
		
		Mockito.when(context.getClient()).thenReturn(client);
		
		assertEquals(new FTPResponse(200, "ok").toString(), cmd.execute(context).toString());
		Mockito.verify(client).setDataAddr("0:0:0:0:0:0:0:0");
		Mockito.verify(client).setDataPort(12345);
	}
	
	@Test
	public void testEprtFail() {
		final FTPRequestEprt cmd = new FTPRequestEprt("toto");
		final Context context = Mockito.mock(Context.class);
		final Client client = Mockito.mock(Client.class);
		
		Mockito.when(context.getClient()).thenReturn(client);	
		
		assertEquals(new FTPResponse(501, "Syntax error in parameters or arguments.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestEprt cmd = new FTPRequestEprt("toto");
		assertEquals(Commands.EPRT, cmd.getCommand());
	}
}
