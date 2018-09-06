package ru.innopolis.stc12.sockets.Chat;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientListener extends Thread {
    private BufferedReader reader;

    public ClientListener(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
