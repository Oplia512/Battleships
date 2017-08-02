package com.javaAcademy.network.input.core;

import com.javaAcademy.logic.stateMachine.core.OnMessageReceiverListener;
import com.javaAcademy.network.core.SocketProcessor;

/**
 * Created by Siarhei Shauchenka on 28.07.17.
 */
public interface InputDataProcessor extends SocketProcessor {

    void messageReceived(String message);
    void setMessageListener(OnMessageReceiverListener messageListener);

}
