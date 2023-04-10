package org.campus02.guessnumber;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class GuessNumberHandler {

    private Socket client;

    public GuessNumberHandler(Socket client) {
        this.client = client;
    }

    public void start() {
        try (BufferedReader read = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter send = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
            int random = new Random().nextInt(32) + 1; // Werte von 0 bis 32
            send.write("Rate meine Zahl");
            send.newLine();
            send.flush();

            String clientInput;
            while ((clientInput = read.readLine()) != null) {
                try {
                    int guess = Integer.parseInt(clientInput);
                    if (guess > 32 || guess < 0) {
                        send.write("Es sind nur Werte von 0 bis inkl. 32 erlaubt");
                    } else if (guess > random) {
                        send.write("Nummer zu hoch. Versuche es erneut");
                    } else {
                        send.write("Nummer zu klein. Versuche es erneut");
                    }
                } catch (NumberFormatException e) {
                    send.write("Bitte geben Sie eine Zahl ein");
                } finally {
                    send.newLine();
                    send.flush();
                }
            }
            send.write("Gratulation. Nummer erraten");
            send.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
