package com.ftp.utils;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import com.ftp.cmd.Commands;
import com.ftp.cmd.requests.FTPRequest;

public class ParserTest {
	
	@Test
	public void testParseCommandeSimple() throws ParseException {
		final FTPRequest request = Parser.parseRequest("LIST");
		assertEquals(request.getCommand(), Commands.LIST);
	}
	
	@Test
	public void testParseCommandeArgs() throws ParseException {
		final FTPRequest request = Parser.parseRequest("CWD toto");
		assertEquals(Commands.CWD, request.getCommand());
		assertEquals("toto", request.getMessage());
	}
	
	@Test(expected=ParseException.class)
	public void testParseWithNotEnouthParameters() throws ParseException {
		Parser.parseRequest("");
	}
	
	@Test(expected=ParseException.class)
	public void testParseCommandeNotExists() throws ParseException {
		Parser.parseRequest("TATA");
	}
}
