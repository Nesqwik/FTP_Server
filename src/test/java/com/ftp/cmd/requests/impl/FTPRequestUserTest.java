
package com.ftp.cmd.requests.impl;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.ftp.cmd.Commands;
import com.ftp.cmd.FTPResponse;
import com.ftp.utils.Context;

public class FTPRequestUserTest {
	
	@Test
	public void testUserOk() {
		final FTPRequestUser cmdUser = new FTPRequestUser("toto");
		final Context context = Mockito.mock(Context.class);
		assertEquals(new FTPResponse(331, "User name ok, need password").toString(), cmdUser.execute(context).toString());
	}
	
	@Test
	public void testGetCommand() {
		final FTPRequestUser cmdUser = new FTPRequestUser("toto");
		assertEquals(Commands.USER, cmdUser.getCommand());
	}
}
