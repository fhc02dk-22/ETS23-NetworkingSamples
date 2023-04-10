package org.campus02.guessnumber;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class GuessNumberServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2222)) {
            while (true) {
                try (Socket client = serverSocket.accept()) {
                    GuessNumberHandler guessNumberHandler = new GuessNumberHandler(client);
                    guessNumberHandler.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
