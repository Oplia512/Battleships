package com.java_academy;

import com.java_academy.logic.Game;
import com.java_academy.logic.model.MessageObject;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class App {

    public static void main(String[] args) {
        Supplier<String> supplier = new Scanner(System.in)::nextLine;
        Consumer<MessageObject> consumer = message -> System.out.println(message.getMessage());

        Game game = new Game(supplier, consumer);
        game.startGame();

    }
}
