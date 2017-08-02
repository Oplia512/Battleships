package com.javaAcademy.network.output;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.javaAcademy.network.Connector;
import com.javaAcademy.network.output.core.OutputDataProcessor;

/**
 * Created by Siarhei Shauchenka on 28.07.17.
 */
public class SocketOutputDataProcessor implements OutputDataProcessor {

    private DataOutputStream dataOutputStream;
    private Socket mSocket;
    private String mMessage;

    @Override
    public void sendMessage(String message) {
        if (message != null && !message.isEmpty() && !mSocket.isClosed() && dataOutputStream != null) {
            mMessage = message;
            Connector.getExecutor().execute(this);
        }
    }

    @Override
    public void setSocket(Socket socket) {
        mSocket = socket;
        if (socket.isConnected() && !socket.isClosed()) {
            try {
                dataOutputStream = new DataOutputStream(mSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeSocket() {
        try {
            if (mSocket != null) {
                mSocket.close();
            }
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
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
