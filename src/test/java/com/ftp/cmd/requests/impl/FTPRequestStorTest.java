package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import java.io.DataInputStream;
import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.Client;
import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;
import com.ftp.utils.FileSystem;

public class FTPRequestStorTest {
	
	@Test
	public void testStorOk() throws IOException {
		final FTPRequestStor cmd = new FTPRequestStor("toto.txt");
		final Context context = Mockito.mock(Context.class);
		final Client client = Mockito.mock(Client.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);

		Mockito.when(client.getDataInputStream()).thenReturn(Mockito.mock(DataInputStream.class));
		Mockito.when(context.getClient()).thenReturn(client);
		Mockito.when(context.getFileSystem()).thenReturn(fs);
		
		Mockito.doNothing().when(fs).writeFileToSystem(any(String.class), any(DataInputStream.class));
		Mockito.doNothing().when(client).sendResponse(any(FTPResponse.class));
		
		assertEquals(new FTPResponse(226, "Closing data connection. Requested file action successful.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testStorFail() throws IOException {
		final FTPRequestStor cmd = new FTPRequestStor("toto.txt");
		final Context context = Mockito.mock(Context.class);
		final Client client = Mockito.mock(Client.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);

		Mockito.when(client.getDataInputStream()).thenReturn(Mockito.mock(DataInputStream.class));
		Mockito.when(context.getClient()).thenReturn(client);
		Mockito.when(context.getFileSystem()).thenReturn(fs);
		
		Mockito.doThrow(IOException.class).when(fs).writeFileToSystem(any(String.class), any(DataInputStream.class));
		Mockito.doNothing().when(client).sendResponse(any(FTPResponse.class));
		
		assertEquals(new FTPResponse(451, "Requested action aborted: local error in processing.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestStor cmd = new FTPRequestStor("");
		assertEquals(Commands.STOR, cmd.getCommand());
	}
}
