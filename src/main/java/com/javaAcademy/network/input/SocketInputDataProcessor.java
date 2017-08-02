package com.javaAcademy.network.input;

import static com.javaAcademy.network.socketProvider.core.AbstractSocketProvider.CLOSE_MESSAGE;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import com.javaAcademy.logic.stateMachine.core.OnMessageReceiverListener;
import com.javaAcademy.network.input.core.InputDataProcessor;

/**
 * Created by Siarhei Shauchenka on 28.07.17.
 */
public class SocketInputDataProcessor implements InputDataProcessor {

    private Socket mSocket;
    private OnMessageReceiverListener messageReceiverListener;

    @Override
    public void setSocket(Socket socket) {
        mSocket = socket;
    }

    @Override
    public void messageReceived(String message) {
        if (messageReceiverListener != null){
            messageReceiverListener.onMessageReceived(() -> message);
        }
    }

    @Override
    public void setMessageListener(OnMessageReceiverListener messageListener) {
        this.messageReceiverListener = messageListener;
    }

    @Override
    public void closeSocket() {
        try {
            mSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(mSocket.getInputStream())){
            while (!Thread.interrupted()){
                String input = dataInputStream.readUTF();
                if (input.equals(CLOSE_MESSAGE)){
                    Thread.currentThread().interrupt();
                }
                messageReceived(input);
            }

        } catch (SocketException | EOFException ex){
            System.out.println("Socket closed!");
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("InputProcessor execution terminated!");
    }

}
