package com.ftp;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import org.junit.Test;

public class ClientTest {
	
	@Test(expected=SocketException.class)
	public void testClientCreationFailIfSocketNotConnected() throws IOException {
		new Client(new Socket());
	}
}
