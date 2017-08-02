package com.javaAcademy.network;

import org.testng.annotations.Test;

import com.javaAcademy.network.Connector;
import com.javaAcademy.network.socketProvider.ClientSocketProvider;
import com.javaAcademy.network.socketProvider.core.SocketProvider;

import java.net.Socket;

import static org.testng.Assert.assertNotNull;

/**
 * @author Siarhei Shauchenka
 * @since 31.07.17.
 */

@Test
public class ConnectorTest {

    public void creationInstanceTest() {
        Socket socket = new Socket();
        SocketProvider socketProvider = new ClientSocketProvider(socket);
        Connector connector = new Connector(socketProvider);
        assertNotNull(connector);
    }
}
