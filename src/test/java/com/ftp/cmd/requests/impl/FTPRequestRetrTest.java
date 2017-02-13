package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.Client;
import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;
import com.ftp.utils.FileSystem;

public class FTPRequestRetrTest {
	@Test
	public void testRetrOk() throws IOException {
		final FTPRequestRetr cmd = new FTPRequestRetr("toto.txt");
		final Context context = Mockito.mock(Context.class);
		final Client client = Mockito.mock(Client.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		final DataOutputStream dos = Mockito.mock(DataOutputStream.class);
		
		Mockito.when(client.getDataOutputStream()).thenReturn(dos);
		Mockito.when(context.getClient()).thenReturn(client);
		Mockito.when(context.getFileSystem()).thenReturn(fs);
		
		Mockito.doNothing().when(dos).close();
		Mockito.doNothing().when(fs).writeFileToBuffer(any(String.class), any(DataOutputStream.class));
		Mockito.doNothing().when(client).sendResponse(any(FTPResponse.class));
		
		assertEquals(new FTPResponse(200, "ok").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testRetrFail() throws IOException {
		final FTPRequestRetr cmd = new FTPRequestRetr("toto.txt");
		final Context context = Mockito.mock(Context.class);
		final Client client = Mockito.mock(Client.class);
		final FileSystem fs = Mockito.mock(FileSystem.class);
		final DataOutputStream dos = Mockito.mock(DataOutputStream.class);

		Mockito.when(client.getDataOutputStream()).thenReturn(dos);
		Mockito.when(context.getClient()).thenReturn(client);
		Mockito.when(context.getFileSystem()).thenReturn(fs);
		
		Mockito.doNothing().when(dos).close();
		Mockito.doThrow(IOException.class).when(fs).writeFileToBuffer(any(String.class), any(DataOutputStream.class));
		Mockito.doNothing().when(client).sendResponse(any(FTPResponse.class));
		
		assertEquals(new FTPResponse(451, "Requested action aborted: local error in processing.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestRetr cmd = new FTPRequestRetr("toto");
		assertEquals(Commands.RETR, cmd.getCommand());
	}
}
