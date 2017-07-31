package com.java_academy.network;

import com.java_academy.network.socket_provider.core.SocketProvider;

import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by Siarhei Shauchenka on 28.07.17.
 */
public class Connector {

    private final static ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(5);



    public synchronized static ScheduledExecutorService getExecutor() {
        return executor;
    }

    private SocketProvider socketProvider;

    public Connector(SocketProvider socketProvider) {
        this.socketProvider = socketProvider;
    }

    public void connect(InetSocketAddress address){
        socketProvider.connect(address);
    }

    public void closeConnection(){
        socketProvider.close();
    }
}
