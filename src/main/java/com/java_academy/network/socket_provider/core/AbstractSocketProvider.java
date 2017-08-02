package com.java_academy.network.socket_provider.core;

import com.java_academy.logic.state_machine.core.OnMessageReceiverListener;
import com.java_academy.network.Connector;
import com.java_academy.network.input.core.InputDataProcessor;
import com.java_academy.network.output.core.OutputDataProcessor;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Supplier;

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

    protected void setInputProcessors(Supplier<InputDataProcessor> inputDataProcessorSupplier, Socket socket) throws IOException {
        InputDataProcessor inputDataProcessor = inputDataProcessorSupplier.get();
        inputDataProcessor.setSocket(socket);
        inputDataProcessor.setMessageListener(messageReceiverListener);
        Connector.getExecutor().execute(inputDataProcessor);
    }

    protected OutputDataProcessor setOutputProcessors(Supplier<OutputDataProcessor> outputDataProcessorSupplier, Socket socket) throws IOException {
        OutputDataProcessor outputDataProcessor = outputDataProcessorSupplier.get();
        outputDataProcessor.setSocket(socket);
        return outputDataProcessor;
    }

}
