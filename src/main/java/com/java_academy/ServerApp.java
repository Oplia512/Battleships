package com.java_academy;

import com.java_academy.logic.Game;
import com.java_academy.logic.model.MessageObject;
import com.java_academy.logic.model.Players;
import com.java_academy.network.Connector;
import com.java_academy.network.socket_provider.ServerSocketProvider;
import com.java_academy.network.socket_provider.core.SocketProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.function.Consumer;

import static com.java_academy.network.socket_provider.core.AbstractSocketProvider.CLOSE_MESSAGE;

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

/*            connector.addMessageReseiverListenerToSocketProvider(messageSupplier -> {
                System.out.println("message from a Client: " + messageSupplier.get());
                if (messageSupplier.get().equals(CLOSE_MESSAGE)){
                    connector.sendMessage(new MessageObject(Players.FIRST_PLAYER, CLOSE_MESSAGE));
                    connector.sendMessage(new MessageObject(Players.SECOND_PLAYER, CLOSE_MESSAGE));
                    connector.closeConnection();
                }
            });
*/
            Game game = new Game(connector::sendMessage);
            connector.addMessageReceiverListenerToSocketProvider(game);
            if (connector.connect(inetSocketAddress)){
                game.startGame();

                /*String message = "HELLO from Server";
                System.out.println("Sending message: \"" + message + "\" to clients");
                connector.sendMessage(new MessageObject(Players.FIRST_PLAYER, message));
                connector.sendMessage(new MessageObject(Players.SECOND_PLAYER, message));*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
