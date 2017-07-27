package com.java_academy.network;

import com.java_academy.network.InputOutputStreams.DataReader;
import com.java_academy.network.InputOutputStreams.DataWriter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class ClientMain {

	public static void main(String[] args) {
		try(Client client = Client.getClient("localhost", 6666)) {
			DataWriter writer = new DataWriter(new DataOutputStream(client.getSocket().getOutputStream()));
		writer.writeMessage("dupa dupa \n");
			DataReader reader = new DataReader(new BufferedReader(new InputStreamReader(client.getSocket().getInputStream())));
			reader.readMessage();
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

}
