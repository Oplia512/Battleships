package com.javaAcademy;

import static com.javaAcademy.network.socketProvider.core.AbstractSocketProvider.CLOSE_MESSAGE;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.function.Supplier;

import com.javaAcademy.logic.model.MessageObject;
import com.javaAcademy.logic.model.Players;
import com.javaAcademy.logic.stateMachine.core.OnMessageReceiverListener;
import com.javaAcademy.network.Connector;
import com.javaAcademy.network.socketProvider.ClientSocketProvider;
import com.javaAcademy.network.socketProvider.ServerSocketProvider;
import com.javaAcademy.network.socketProvider.core.SocketProvider;

/**
 * @author Siarhei Shauchenka
 * @since 01.08.17
 *
 * Temporary Server Application for Connection checking
 */
public class ServerApp {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            SocketProvider socketProvider = new ServerSocketProvider(serverSocket);

            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 3000);

            Connector connector = new Connector(socketProvider);

            connector.addMessageReseiverListenerToSocketProvider(messageSupplier -> {
                System.out.println("message from a Client: " + messageSupplier.get());
                if (messageSupplier.get().equals(CLOSE_MESSAGE)){
                    connector.sendMessage(new MessageObject(Players.FIRST_PLAYER, CLOSE_MESSAGE));
                    connector.sendMessage(new MessageObject(Players.SECOND_PLAYER, CLOSE_MESSAGE));
                    connector.closeConnection();
                }
            });

            if (connector.connect(inetSocketAddress)){
                String message = "HELLO from Server";
                System.out.println("Sending message: \"" + message + "\" to clients");
                connector.sendMessage(new MessageObject(Players.FIRST_PLAYER, message));
                connector.sendMessage(new MessageObject(Players.SECOND_PLAYER, message));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
