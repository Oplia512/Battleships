package com.java_academy.network.input;

import com.java_academy.network.Connector;
import com.java_academy.network.input.core.InputDataProcessor;
import org.testng.annotations.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Siarhei Shauchenka
 * @since 31.07.17
 */


public class SocketInputDataProcessorTest {

    private final InetSocketAddress CORRECT_ADDRESS = new InetSocketAddress("localhost", 3000);
    private final String TEST_MESSAGE = "test_message";

    @Test
    public void creationInstanceTest(){
        InputDataProcessor processor = new SocketInputDataProcessor();
        assertNotNull(processor);
    }

    @Test
    public void clientSideTest(){
        InputDataProcessor processor = new SocketInputDataProcessor();
        Socket clientSocket = new Socket();
        Connector.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                createServerSocket();
            }
        });

        Connector.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                createClientSocket(clientSocket, processor);
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(clientSocket.isClosed(), true);
    }

    @Test
    public void closeSocketTest(){
        InputDataProcessor processor = new SocketInputDataProcessor();
        Socket socket = new Socket();
        processor.setSocket(socket);
        processor.closeSocket();
        assertEquals(socket.isClosed(), true);
    }

    private void createClientSocket(Socket clientSocket, InputDataProcessor processor){
        processor.setSocket(clientSocket);
        processor.setMessageListener(messageSupplier -> {
            assertEquals(messageSupplier.get(), TEST_MESSAGE);
        });
        try {
            clientSocket.connect(CORRECT_ADDRESS);
            Connector.getExecutor().execute(processor);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream())){
            dataOutputStream.writeUTF(TEST_MESSAGE);
            dataOutputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
