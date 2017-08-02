package com.javaAcademy.network.socketProvider.core;

import java.net.InetSocketAddress;

import com.javaAcademy.logic.model.MessageObject;
import com.javaAcademy.logic.stateMachine.core.OnMessageReceiverListener;

/**
 * Created by Siarhei Shauchenka on 28.07.17.
 */
public interface SocketProvider {

    boolean connect(InetSocketAddress address);
    void sendMessage(MessageObject messageObject);
    void close(OnSocketCloseListener closeListener);
    void setMessageReceiverListener(OnMessageReceiverListener messageReceiveListener);

}
