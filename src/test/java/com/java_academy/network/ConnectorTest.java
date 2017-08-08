package com.java_academy.network;

import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.model.Players;
import com.java_academy.network.socket_provider.ClientSocketProvider;
import com.java_academy.network.socket_provider.core.SocketProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertNotNull;

/**
 * @author Siarhei Shauchenka
 * @since 31.07.17.
 */

public class ConnectorTest {

    private SocketProvider mockSocketProvider;
    private Connector connector;

    @BeforeTest
    public void prepareTests() {
        mockSocketProvider = mock(ClientSocketProvider.class);
    }

    @Test(priority = 1)
    public void creationInstanceTest() {
        connector = new Connector(mockSocketProvider);
        assertNotNull(connector);
    }

    @Test(priority = 2)
    public void addMessageReceiverListenerToSocketProviderTest() {
        connector.addMessageReceiverListenerToSocketProvider(null);
        verify(mockSocketProvider, times(1)).setMessageReceiverListener(null);
    }

    @Test(priority = 3)
    public void connectTest() {
        connector.connect(null);
        verify(mockSocketProvider, times(1)).connect(null);
    }

    @Test(priority = 4)
    public void closeConnectionTest() {
        //given
        Connector givenConnector = new Connector(mockSocketProvider);

        //when
        doNothing().when(mockSocketProvider).close(any());
        givenConnector.closeConnection();
        //then
        verify(mockSocketProvider, times(1)).close(any());

    }

    @Test(priority = 5)
    public void sendMessageTest() {
        MessageObject message = new MessageObject(Players.FIRST_PLAYER, "TEST");
        connector.sendMessage(message);
        verify(mockSocketProvider, times(1)).sendMessage(message);
    }

    @Test(priority = 6)
    public void getInstanceOfExecutorTest() {
        assertNotNull(Connector.getExecutor());
    }

}
