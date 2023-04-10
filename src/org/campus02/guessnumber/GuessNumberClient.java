package org.campus02.guessnumber;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GuessNumberClient {
    public static void main(String[] args) {
        try (Socket s = new Socket("localhost", 2222);
             BufferedWriter send = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
             BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));
             BufferedReader cli = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String line;
            // lese vom server
            while ((line = read.readLine()) != null) {
                System.out.println(line);
                if (line.startsWith("Gratulation")) {
                    // hurra, nummer erraten
                    break;
                }
                // user input
                String input = cli.readLine();
                // sende user input an server
                send.write(input);
                send.newLine();
                send.flush();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
