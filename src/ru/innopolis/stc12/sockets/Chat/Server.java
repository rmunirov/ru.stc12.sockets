package ru.innopolis.stc12.sockets.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    private final List<ClientCopy> clientCopies = new ArrayList<>();
    private final Integer PORT_NUMBER = 4999;
    private Integer connectCount = 0;

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            while (true) {
                Socket socket = serverSocket.accept();
                connectCount++;
                ClientCopy clientCopy = new ClientCopy(socket, "Client" + connectCount, clientCopies);
                clientCopy.sendMessage("Connect done, your login - Client" + connectCount);
                synchronized (clientCopies) {
                    clientCopies.add(clientCopy);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
