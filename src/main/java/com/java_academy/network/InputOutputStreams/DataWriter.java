package com.java_academy.network.InputOutputStreams;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by artur on 27.07.17.
 */
public class DataWriter {

    private DataOutputStream dataOutputStream;


    public DataWriter(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public void writeMessage(String message) throws IOException {
        dataOutputStream.writeBytes(message);
    }

}
