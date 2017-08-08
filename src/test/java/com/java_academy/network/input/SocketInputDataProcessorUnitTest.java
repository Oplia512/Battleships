package com.java_academy.network.input;

import com.java_academy.network.input.core.InputDataProcessor;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.Socket;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertNotNull;

/**
 * @author Siarhei Shauchenka
 * @since 31.07.17
 */


public class SocketInputDataProcessorUnitTest {

    @Test(priority = 1)
    public void creationInstanceTest() {
        //given
        InputDataProcessor processor = new SocketInputDataProcessor();
        //then
        assertNotNull(processor);
    }

    @Test(priority = 2)
    public void closeSocketTest() throws IOException {
        //given
        InputDataProcessor processor = new SocketInputDataProcessor();
        Socket mockSocket = mock(Socket.class);
        processor.setSocket(mockSocket);
        //when
        processor.closeSocket();
        //then
        verify(mockSocket, times(1)).close();
    }
}
