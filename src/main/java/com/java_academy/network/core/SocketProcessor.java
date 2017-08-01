package com.java_academy.network.core;

import java.net.Socket;

/**
 * Created by Siarhei Shauchenka on 28.07.17.
 */
public interface SocketProcessor extends Runnable{

    void setSocket(Socket socket);
    void closeSocket();
}
