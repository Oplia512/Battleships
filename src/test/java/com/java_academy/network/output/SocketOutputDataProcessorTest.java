package com.java_academy.network.output;

import com.java_academy.network.Connector;
import com.java_academy.network.output.core.OutputDataProcessor;
import org.testng.annotations.Test;

import java.io.DataInputStream;
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
public class SocketOutputDataProcessorTest {

    private final InetSocketAddress CORRECT_ADDRESS = new InetSocketAddress("localhost", 4000);
    private final String TEST_MESSAGE = "test_message";

    @Test(priority = 1)
    public void creationInstanceTest() {
        System.out.println("----------SocketOutputDataProcessorTest---------------");
        System.out.println();

        OutputDataProcessor processor = new SocketOutputDataProcessor();
        assertNotNull(processor);
    }

    @Test(priority = 2)
    public void closeSocketTest() {
        OutputDataProcessor processor = new SocketOutputDataProcessor();
        Socket socket = new Socket();
        processor.setSocket(socket);
        processor.closeSocket();
        assertEquals(socket.isClosed(), true);
    }

    @Test(priority = 3)
    public void clientSideTest() {

        OutputDataProcessor processor = new SocketOutputDataProcessor();
        Socket clientSocket = new Socket();
        Connector.getExecutor().execute(this::createServerSocket);

        Connector.getExecutor().execute(() -> connectClientSocket(clientSocket, processor));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void connectClientSocket(Socket clientSocket, OutputDataProcessor processor) {
        try {
            clientSocket.connect(CORRECT_ADDRESS);
            processor.setSocket(clientSocket);
            processor.sendMessage(TEST_MESSAGE);
            System.out.println("message: " + TEST_MESSAGE + " was sent to the server");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
             DataInputStream dataInputStream = new DataInputStream(client.getInputStream())) {
            String input = dataInputStream.readUTF();
            assertEquals(input, TEST_MESSAGE);
            System.out.println("message: " + TEST_MESSAGE + " was received from the client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
