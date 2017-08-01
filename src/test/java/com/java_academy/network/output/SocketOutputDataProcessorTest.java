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

    @Test
    public void creationInstanceTest(){
        OutputDataProcessor processor = new SocketOutputDataProcessor();
        assertNotNull(processor);
    }

    @Test
    public void closeSocketTest(){
        OutputDataProcessor processor = new SocketOutputDataProcessor();
        Socket socket = new Socket();
        processor.setSocket(socket);
        processor.closeSocket();
        assertEquals(socket.isClosed(), true);
    }

    @Test
    public void clientSideTest(){
        OutputDataProcessor processor = new SocketOutputDataProcessor();
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
                connectClientSocket(clientSocket, processor);
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void connectClientSocket(Socket clientSocket, OutputDataProcessor processor){
        try {
            clientSocket.connect(CORRECT_ADDRESS);
            processor.setSocket(clientSocket);
            processor.sendMessage(TEST_MESSAGE);
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
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream())){
            String input = dataInputStream.readUTF();
            assertEquals(input, TEST_MESSAGE);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
