package com.ftp.cmd.requests.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;

public class FTPRequestPassTest {
	@Test
	public void testPASSOk() throws IOException {
		final FTPRequestPass cmd = new FTPRequestPass("toto");
		final Context context = Mockito.mock(Context.class);
		
		Mockito.doReturn("toto").when(context).getUsername();
		
		assertEquals(new FTPResponse(230, "User logged in.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testPASSFailBadPassword() throws IOException {
		final FTPRequestPass cmd = new FTPRequestPass("toto");
		final Context context = Mockito.mock(Context.class);
		
		Mockito.doReturn("tata").when(context).getUsername();
		
		assertEquals(new FTPResponse(530, "Not logged in.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testPASSFailBadUser() throws IOException {
		final FTPRequestPass cmd = new FTPRequestPass("tata");
		final Context context = Mockito.mock(Context.class);
		
		Mockito.doReturn("toto").when(context).getUsername();
		
		assertEquals(new FTPResponse(530, "Not logged in.").toString(), cmd.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestPass cmd = new FTPRequestPass("toto");
		assertEquals(Commands.PASS, cmd.getCommand());
	}
}
