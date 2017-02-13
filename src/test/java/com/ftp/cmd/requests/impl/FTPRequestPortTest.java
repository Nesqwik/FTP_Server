package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.Client;
import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;

public class FTPRequestPortTest {
	@Test
	public void testPortOk() throws IOException {
		final FTPRequestPort cmd = new FTPRequestPort("127,0,0,1,4,1");
		final Context context = Mockito.mock(Context.class);
		final Client client = Mockito.mock(Client.class);
		
		Mockito.when(context.getClient()).thenReturn(client);
		
		assertEquals(new FTPResponse(200, "Command okay.").toString(), cmd.execute(context).toString());
		Mockito.verify(client).setDataAddr("127.0.0.1");
		Mockito.verify(client).setDataPort(1025);
	}
	
	@Test
	public void testPortFail() throws IOException {
		final FTPRequestPort cmd = new FTPRequestPort("toto.txt");
		final Context context = Mockito.mock(Context.class);
		final Client client = Mockito.mock(Client.class);

		Mockito.when(context.getClient()).thenReturn(client);		
		assertEquals(new FTPResponse(501, "Syntax error in parameters or arguments.").toString(), cmd.execute(context).toString());
		Mockito.verifyZeroInteractions(client);
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestPort cmd = new FTPRequestPort("toto");
		assertEquals(Commands.PORT, cmd.getCommand());
	}
}
