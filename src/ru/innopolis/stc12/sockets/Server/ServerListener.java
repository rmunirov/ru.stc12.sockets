package ru.innopolis.stc12.sockets.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread {
    private Integer port;

    public ServerListener(Integer port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
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
