package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.Client;
import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;

public class FTPRequestEpsvTest {
	@Test
	public void testEpsvOk() throws IOException {
		final FTPRequestEpsv cmd = new FTPRequestEpsv("toto");
		final Context context = Mockito.mock(Context.class);
		final Client client = Mockito.mock(Client.class);
		
		Mockito.when(client.getDataPort()).thenReturn(4242);
		Mockito.when(context.getClient()).thenReturn(client);
		
		assertEquals(new FTPResponse(229, "Entering Extended Passive Mode (|||4242|).").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testEpsvFailUnknownHostException() throws UnknownHostException, IOException {
		final FTPRequestEpsv cmd = new FTPRequestEpsv("toto");
		final Context context = Mockito.mock(Context.class);
		final Client client = Mockito.mock(Client.class);
		
		Mockito.doThrow(UnknownHostException.class).when(client).connectPassiveDataSocket();
		Mockito.when(context.getClient()).thenReturn(client);	
		
		assertEquals(new FTPResponse(550, "Requested action not taken.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testEpsvFailIOException() throws UnknownHostException, IOException {
		final FTPRequestEpsv cmd = new FTPRequestEpsv("toto");
		final Context context = Mockito.mock(Context.class);
		final Client client = Mockito.mock(Client.class);
		
		Mockito.doThrow(IOException.class).when(client).connectPassiveDataSocket();
		Mockito.when(context.getClient()).thenReturn(client);	
		
		assertEquals(new FTPResponse(550, "Requested action not taken.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestEpsv cmd = new FTPRequestEpsv("toto");
		assertEquals(Commands.EPSV, cmd.getCommand());
	}
}
