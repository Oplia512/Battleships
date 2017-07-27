package com.java_academy.network.InputOutputStreams;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by artur on 27.07.17.
 */
public class DataReader {

    private BufferedReader dataInputStream;


    public DataReader(BufferedReader dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public void readMessage() throws IOException {
        System.out.println(dataInputStream.readLine());
    }
}
