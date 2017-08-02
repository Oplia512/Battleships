package com.javaAcademy;

import static com.javaAcademy.network.socketProvider.core.AbstractSocketProvider.CLOSE_MESSAGE;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.javaAcademy.logic.model.MessageObject;
import com.javaAcademy.logic.model.Players;
import com.javaAcademy.logic.stateMachine.core.OnMessageReceiverListener;
import com.javaAcademy.network.Connector;
import com.javaAcademy.network.socketProvider.ClientSocketProvider;
import com.javaAcademy.network.socketProvider.core.SocketProvider;

/**
 * @author Siarhei Shauchenka
 * @since 01.08.17
 *
 * Temporary ClientApp for Connection check
 */
public class ClientApp {

    public static void main(String[] args) {

        Socket socket = new Socket();

        SocketProvider socketProvider = new ClientSocketProvider(socket);

        Connector connector = new Connector(socketProvider);

        connector.addMessageReseiverListenerToSocketProvider(messageSupplier -> System.out.println("message from the server: " + messageSupplier.get()));

        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 3000);
        if (connector.connect(inetSocketAddress)){
            String message = "HELLO from Client";
            System.out.println("Sending message \"" + message + "\" to the server");
            connector.sendMessage(new MessageObject(Players.FIRST_PLAYER, message));

            Connector.getExecutor().schedule(new Runnable() {
                @Override
                public void run() {
                    connector.sendMessage(new MessageObject(Players.FIRST_PLAYER, CLOSE_MESSAGE));
                    connector.closeConnection();
                }
            }, 4, TimeUnit.SECONDS);

        }

    }
}
