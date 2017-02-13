package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.Client;
import com.ftp.cmd.Commands;
import com.ftp.utils.Context;

public class FTPRequestPASVTest {
	@Test
	public void testPASVOk() throws IOException {
		final FTPRequestPASV cmd = new FTPRequestPASV("127,0,0,1,4,1");
		final Context context = Mockito.mock(Context.class);
		final Client client = Mockito.mock(Client.class);
		
		Mockito.when(context.getClient()).thenReturn(client);
		
		assertEquals(227, cmd.execute(context).getCode());
		Mockito.verify(client).connectDataSocket();
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestPASV cmd = new FTPRequestPASV("toto");
		assertEquals(Commands.PASV, cmd.getCommand());
	}
}
