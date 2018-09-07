package ru.innopolis.stc12.sockets.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientListener extends Thread {
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(Client.CLIENT_PORT)) {
            Socket socket = serverSocket.accept();
            InputStream fromServer = socket.getInputStream();
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(fromServer));

            String message = null;
            while ((message = serverReader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
