package ru.innopolis.stc12.sockets.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    public static List<ClientCopy> clientCopies = new ArrayList<>();
    private final Integer PORT_NUMBER = 4999;
    private Integer connectCount = 0;

    public Server() {
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                connectCount++;
                ClientCopy clientCopy = new ClientCopy(socket, "Client" + connectCount);
                clientCopy.sendMessage("Connect done, your login - Client" + connectCount);
                clientCopies.add(clientCopy);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
