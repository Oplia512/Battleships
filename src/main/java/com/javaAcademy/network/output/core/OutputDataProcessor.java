package com.javaAcademy.network.output.core;

import com.javaAcademy.network.core.SocketProcessor;

/**
 * Created by Siarhei Shauchenka on 28.07.17.
 */
public interface OutputDataProcessor extends SocketProcessor {
    void sendMessage(String message);
}
