package com.javaAcademy.network.socketProvider.core;

import java.net.Socket;
import java.util.function.Supplier;

import com.javaAcademy.logic.stateMachine.core.OnMessageReceiverListener;
import com.javaAcademy.network.Connector;
import com.javaAcademy.network.input.core.InputDataProcessor;
import com.javaAcademy.network.output.core.OutputDataProcessor;

/**
 * Created by Siarhei Shauchenka on 28.07.17.
 */
public abstract class AbstractSocketProvider implements SocketProvider{

    public final static String CLOSE_MESSAGE = "CLOSE";

    protected OnMessageReceiverListener messageReceiverListener;

    @Override
    public void setMessageReceiverListener(OnMessageReceiverListener messageReceiverListener){
        this.messageReceiverListener = messageReceiverListener;
    }

    protected void setInputProcessors(Supplier<InputDataProcessor> inputDataProcessorSupplier, Socket socket) {
        InputDataProcessor inputDataProcessor = inputDataProcessorSupplier.get();
        inputDataProcessor.setSocket(socket);
        inputDataProcessor.setMessageListener(messageReceiverListener);
        Connector.getExecutor().execute(inputDataProcessor);
    }

    protected OutputDataProcessor setOutputProcessors(Supplier<OutputDataProcessor> outputDataProcessorSupplier, Socket socket) {
        OutputDataProcessor outputDataProcessor = outputDataProcessorSupplier.get();
        outputDataProcessor.setSocket(socket);
        return outputDataProcessor;
    }

}
