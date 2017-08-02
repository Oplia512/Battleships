package com.java_academy.logic.tools;

import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

public class BSLogTest {
	
	@Test
	public void loggerIsNotNull() {
		assertFalse(BSLog.logger == null);
	}
	
	@Test
	public void testErrorLog() {
		BSLog.error("ERROR");
	}
	
	@Test
	public void testInfoLog() {
		BSLog.info("INFO");
	}
	
	@Test
	public void testWarnLog() {
		BSLog.warn("WARN");
	}
	
	@Test
	public void testDebugLog() {
		BSLog.debug("DEBUG");
	}
}

