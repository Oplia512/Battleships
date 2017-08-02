package com.javaAcademy.network.socketProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.javaAcademy.logic.model.MessageObject;
import com.javaAcademy.logic.stateMachine.core.OnMessageReceiverListener;
import com.javaAcademy.network.input.SocketInputDataProcessor;
import com.javaAcademy.network.output.SocketOutputDataProcessor;
import com.javaAcademy.network.output.core.OutputDataProcessor;
import com.javaAcademy.network.socketProvider.core.AbstractSocketProvider;
import com.javaAcademy.network.socketProvider.core.OnSocketCloseListener;

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
