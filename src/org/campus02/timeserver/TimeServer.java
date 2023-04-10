package org.campus02.timeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    public static void main(String[] args) {
        System.out.println("Erzeuge den Server");
        try(ServerSocket serverSocket = new ServerSocket(1111)) {
            // anzahl an client verbindungen
            int clients = 0;

            // laufe so lange, bis sich insgesamt 5 clients verbunden hatten
            while (clients < 5) {
                System.out.println("Warte auf Clients...");
                try(Socket client = serverSocket.accept()) {
                    clients ++;
                    System.out.println("Client hat sich verbunden");
                    System.out.println("Anzahl bereits verbundener Clients: " + clients);
                    TimeHandler timeHandler = new TimeHandler(client);
                    timeHandler.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
