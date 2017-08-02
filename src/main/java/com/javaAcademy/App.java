package com.javaAcademy;

import java.util.function.Consumer;

import com.javaAcademy.logic.Game;
import com.javaAcademy.logic.model.MessageObject;

public class App {

    public static void main(String[] args) {
        Consumer<MessageObject> consumer = message -> System.out.println(message.getMessage());
        Game game = new Game(consumer);
    }
}
