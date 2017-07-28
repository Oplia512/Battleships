package com.java_academy.network.output;

import com.java_academy.network.Connector;
import com.java_academy.network.output.core.OutputDataProcessor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 28.07.17.
 */
public class SocketOutputDataProcessor implements OutputDataProcessor {

    private DataOutputStream dataOutputStream;
    private Socket mSocket;
    private String mMessage;

    @Override
    public void sendMessage(String message) {
        if (message != null && !message.isEmpty()){
            mMessage = message;
            Connector.getExecutor().execute(this);
        }
    }

    @Override
    public void setSocket(Socket socket) {
        mSocket = socket;
        try {
            dataOutputStream = new DataOutputStream(mSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeSocket() {
        try {
            mSocket.close();
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            dataOutputStream.writeUTF(mMessage);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
