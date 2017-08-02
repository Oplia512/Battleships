package com.javaAcademy.network.socketProvider.core;

/**
 * @author Siarhei Shauchenka
 * @since 01.08.17
 */
@FunctionalInterface
public interface OnSocketCloseListener {
    void socketClosed();
}
