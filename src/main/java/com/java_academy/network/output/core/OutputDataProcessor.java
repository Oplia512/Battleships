package com.java_academy.network.output.core;

import com.java_academy.network.core.SocketProcessor;

/**
 * Created by Siarhei Shauchenka on 28.07.17.
 */
public interface OutputDataProcessor extends SocketProcessor {
    void sendMessage(String message);
}
