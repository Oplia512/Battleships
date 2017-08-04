package com.java_academy;

import com.java_academy.logic.Game;
import com.java_academy.logic.model.MessageObject;

import java.util.function.Consumer;

public class App {

    public static void main(String[] args) {
        Consumer<MessageObject> consumer = message -> System.out.println(message.getMessage());
        Game game = new Game(consumer);
    }
}
