package com.java_academy.logic.state_machine.core;

import java.util.function.Supplier;

/**
 * Created by Siarhei Shauchenka on 28.07.17.
 */
@FunctionalInterface
public interface OnMessageReceiverListener {
    void onMessageReceived(Supplier<String> messageSupplier);
}
