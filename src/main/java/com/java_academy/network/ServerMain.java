package com.java_academy.network;

import com.java_academy.network.InputOutputStreams.DataReader;
import com.java_academy.network.InputOutputStreams.DataWriter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerMain {

	public static void main(String[] args) {
		try(Server server = new Server(6666)) {
			Socket socket = server.acceptClient();
			DataReader reader = new DataReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
			reader.readMessage();
			DataWriter writer = new DataWriter(new DataOutputStream(socket.getOutputStream()));
			writer.writeMessage("twoja stara \n");
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

}
