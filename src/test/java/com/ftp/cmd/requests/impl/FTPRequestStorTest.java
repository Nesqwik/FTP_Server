package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.Client;
import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;

public class FTPRequestStorTest {
	
	@Test
	public void testTypeOk() {
		final FTPRequestStor cmd = new FTPRequestStor("toto.txt");
		final Context context = Mockito.mock(Context.class);
		final Client client = Mockito.mock(Client.class);
		Mockito.doNothing().when(client).sendResponse(any(FTPResponse.class));
		context.setClient(client);
		context.setFileSystem("toto");
		
		assertEquals(new FTPResponse(215, "UNIX").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestStor cmd = new FTPRequestStor("");
		assertEquals(Commands.STOR, cmd.getCommand());
	}
}
