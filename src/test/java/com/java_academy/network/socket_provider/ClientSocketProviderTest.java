package com.java_academy.network.socket_provider;

import com.java_academy.logic.state_machine.core.OnMessageReceiverListener;
import com.java_academy.network.Connector;
import com.java_academy.network.socket_provider.core.SocketProvider;
import org.testng.annotations.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Supplier;

import static org.testng.Assert.assertEquals;

/**
 * @author Siarhei Shauchenka
 * @since 31.07.17
 */
public class ClientSocketProviderTest {


    private final InetSocketAddress CORRECT_ADDRESS = new InetSocketAddress("localhost", 5000);
    private final String TEST_MESSAGE = "test_message";

    @Test
    public void createInstanceTest(){

        Connector.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                createServerSocket();
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Socket socket = new Socket();
        SocketProvider provider = new ClientSocketProvider(socket, new OnMessageReceiverListener() {
            @Override
            public void onMessageReceived(Supplier<String> messageSupplier) {
                assertEquals(messageSupplier.get(), TEST_MESSAGE);
            }
        });
        provider.connect(CORRECT_ADDRESS);

    }



    private void createServerSocket(){
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(CORRECT_ADDRESS, 1);
            connectToClient(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectToClient(ServerSocket serverSocket){
        try(Socket client = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream())){
            String input = dataInputStream.readUTF();
            assertEquals(input, TEST_MESSAGE);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
