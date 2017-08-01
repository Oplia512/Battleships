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
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static org.testng.Assert.assertEquals;

/**
 * @author Siarhei Shauchenka
 * @since 01.08.17
 */

public class ServerSocketProviderTest {

    private final String HOST = "localhost";
    private final int PORT = 6000;
    private final InetSocketAddress CORRECT_ADDRESS = new InetSocketAddress(HOST, PORT);
    private final String TEST_MESSAGE = "test_message";

    @Test
    public void receiveAndSendMessageTest() {

        OnMessageReceiverListener messageReceiverListener = new OnMessageReceiverListener() {
            @Override
            public void onMessageReceived(Supplier<String> messageSupplier) {
                assertEquals(messageSupplier.get(), TEST_MESSAGE);
            }
        };

        SocketProvider provider = null;

        Connector.getExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                connectToServer();
            }
        }, 3, TimeUnit.SECONDS);

        Connector.getExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                connectToServer();
            }
        }, 4, TimeUnit.SECONDS);


        try {
            ServerSocket serverSocket = new ServerSocket();
            provider = new ServerSocketProvider(serverSocket, messageReceiverListener);
            provider.connect(CORRECT_ADDRESS);
            assertEquals(serverSocket.isBound(), true);

            provider.sendMessage(new MessageObject(Players.FIRST_PLAYER, TEST_MESSAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void connectToServer() {
        try (Socket socket = new Socket(HOST, PORT);
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
            dataOutputStream.writeUTF(TEST_MESSAGE);
            dataOutputStream.flush();

            String input = dataInputStream.readUTF();
            assertEquals(input, TEST_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
