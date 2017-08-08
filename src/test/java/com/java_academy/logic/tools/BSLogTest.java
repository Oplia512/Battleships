package com.java_academy.logic.tools;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertNotNull;

public class BSLogTest {

    private Logger logger;
    private Logger mockLogger = mock(Logger.class);

    @BeforeTest
    public void createLoggerInstance() {
        logger = BSLog.getLogger(BSLogTest.class);
    }

    @Test
    public void loggerIsNotNull() {
        assertNotNull(logger);
    }

    @Test
    public void testErrorLog() {
        doNothing().when(mockLogger).error(any());
        BSLog.error(mockLogger, "ERROR");
        verify(mockLogger, times(1)).error(any());
    }

    @Test
    public void testInfoLog() {
        doNothing().when(mockLogger).info(any());
        when(mockLogger.isInfoEnabled()).thenReturn(true);
        BSLog.info(mockLogger, "INFO");
        verify(mockLogger, times(1)).info(any());
    }

    @Test
    public void testWarnLog() {
        doNothing().when(mockLogger).warn(any());
        BSLog.warn(mockLogger, "WARN");
        verify(mockLogger, times(1)).warn(any());
    }

    @Test
    public void testDebugLog() {
        doNothing().when(mockLogger).debug(any());
        when(mockLogger.isDebugEnabled()).thenReturn(true);
        BSLog.debug(mockLogger, "DEBUG");
        verify(mockLogger, times(1)).debug(any());
    }
}

