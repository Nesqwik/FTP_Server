package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.Client;
import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;
import com.ftp.utils.FileSystem;

public class FTPRequestListTest {
	@Test
	public void testListOk() throws IOException {
		final FTPRequestList cmd = new FTPRequestList();
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		final Client client = Mockito.mock(Client.class);
		
		Mockito.doReturn("toto").when(fs).list();
		Mockito.when(context.getFileSystem()).thenReturn(fs);	
		Mockito.when(context.getClient()).thenReturn(client);
		
		
		assertEquals(new FTPResponse(226, "Directory send OK.").toString(), cmd.execute(context).toString());
		Mockito.verify(client).sendStringData("toto");
		
	}
	
	@Test
	public void testListFail() throws InterruptedException {
		final FTPRequestList cmd = new FTPRequestList();
		final Context context = Mockito.mock(Context.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		final Client client = Mockito.mock(Client.class);

		Mockito.doReturn("toto").when(fs).list();
		Mockito.doThrow(InterruptedException.class).when(context).joinConnectionThreadIfAlive();
		Mockito.when(context.getClient()).thenReturn(client);
		Mockito.when(context.getFileSystem()).thenReturn(fs);	
		
		assertEquals(new FTPResponse(550, "Requested action not taken.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestList cmd = new FTPRequestList();
		assertEquals(Commands.LIST, cmd.getCommand());
	}
}
