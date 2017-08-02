package com.javaAcademy.network.socketProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
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
public class ServerSocketProvider extends AbstractSocketProvider{

    private ServerSocket serverSocket;
    private OutputDataProcessor firstPlayerOutputProcessor;
    private OutputDataProcessor secondPlayerOutputProcessor;

    public ServerSocketProvider(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


    @Override
    public boolean connect(InetSocketAddress address) {
        try {
            int BACKLOG = 2;
            serverSocket.bind(address, BACKLOG);
            for (int i = 0; i < BACKLOG; i++) {
                Socket socket = serverSocket.accept();
                setInputProcessors(SocketInputDataProcessor::new, socket);
                switch (i){
                    case 0:
                        firstPlayerOutputProcessor = setOutputProcessors(SocketOutputDataProcessor::new, socket);
                        break;
                    case 1:
                        secondPlayerOutputProcessor = setOutputProcessors(SocketOutputDataProcessor::new, socket);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void sendMessage(MessageObject messageObject) {
        switch (messageObject.getPlayer()){
            case FIRST_PLAYER:
                firstPlayerOutputProcessor.sendMessage(messageObject.getMessage());
                break;
            case SECOND_PLAYER:
                secondPlayerOutputProcessor.sendMessage(messageObject.getMessage());
                break;
        }
    }

    @Override
    public void close(OnSocketCloseListener closeListener) {
        firstPlayerOutputProcessor.closeSocket();
        secondPlayerOutputProcessor.closeSocket();
        if (closeListener != null){
            closeListener.socketClosed();
        }
    }
}
