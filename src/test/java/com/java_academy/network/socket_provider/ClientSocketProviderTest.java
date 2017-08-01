package com.java_academy.network.socket_provider;

import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.model.Players;
import com.java_academy.logic.state_machine.core.OnMessageReceiverListener;
import com.java_academy.network.Connector;
import com.java_academy.network.socket_provider.core.SocketProvider;
import org.testng.annotations.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static org.testng.Assert.assertEquals;

/**
 * @author Siarhei Shauchenka
 * @since 31.07.17
 */
public class ClientSocketProviderTest {


    private final InetSocketAddress CORRECT_ADDRESS = new InetSocketAddress("localhost", 5000);
    private final String TEST_MESSAGE = "test_message";

    @Test
    public void receiveAndSendMessageTest() {

        Connector.getExecutor().execute(this::createServerSocket);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Socket socket = new Socket();

        OnMessageReceiverListener messageReceiverListener = messageSupplier -> assertEquals(messageSupplier.get(), TEST_MESSAGE);

        SocketProvider provider = new ClientSocketProvider(socket, messageReceiverListener);
        provider.connect(CORRECT_ADDRESS);
        assertEquals(socket.isConnected(), true);
        provider.sendMessage(new MessageObject(Players.FIRST_PLAYER, TEST_MESSAGE));
    }

    private void createServerSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(CORRECT_ADDRESS, 1);
            connectToClient(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectToClient(ServerSocket serverSocket) {
        try (Socket client = serverSocket.accept();
             DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(client.getInputStream())) {
            dataOutputStream.writeUTF(TEST_MESSAGE);
            dataOutputStream.flush();

            String input = dataInputStream.readUTF();
            assertEquals(input, TEST_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
