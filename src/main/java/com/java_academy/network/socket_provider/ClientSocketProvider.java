package com.java_academy.network.socket_provider;

import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.state_machine.core.OnMessageReceiverListener;
import com.java_academy.network.input.SocketInputDataProcessor;
import com.java_academy.network.output.SocketOutputDataProcessor;
import com.java_academy.network.output.core.OutputDataProcessor;
import com.java_academy.network.socket_provider.core.AbstractSocketProvider;
import com.java_academy.network.socket_provider.core.OnSocketCloseListener;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 28.07.17.
 */
public class ClientSocketProvider extends AbstractSocketProvider {

    private Socket mSocket;
    private OutputDataProcessor outputDataProcessor;

    public ClientSocketProvider(Socket socket){
        mSocket = socket;
    }

    @Override
    public boolean connect(InetSocketAddress address) {
        try {
            mSocket.connect(address);
            setInputProcessors(SocketInputDataProcessor::new, mSocket);
            outputDataProcessor = setOutputProcessors(SocketOutputDataProcessor::new, mSocket);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void sendMessage(MessageObject messageObject) {
        if (outputDataProcessor != null){
            outputDataProcessor.sendMessage(messageObject.getMessage());
        }
    }

    @Override
    public void close(OnSocketCloseListener closeListener) {
        outputDataProcessor.closeSocket();
        if (closeListener != null){
            closeListener.socketClosed();
        }
    }

}
