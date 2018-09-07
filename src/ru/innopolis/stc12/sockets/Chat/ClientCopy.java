package ru.innopolis.stc12.sockets.Chat;

import java.io.*;
import java.net.Socket;
import java.util.List;

class ClientCopy extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String login;
    private final List<ClientCopy> clientCopies;

    public ClientCopy(Socket socket, String login, List<ClientCopy> clientCopies) throws IOException {
        this.socket = socket;
        this.login = login;
        this.clientCopies = clientCopies;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    public void sendMessage(String text) throws IOException {
        writer.write(text + '\n');
        writer.flush();
    }

    private void sendMessageToAllClients(String text) throws IOException {
        synchronized (clientCopies) {
            for (ClientCopy clientCopy : clientCopies) {
                if (clientCopy == this) {
                    continue;
                }
                clientCopy.writer.write(login + " > " + text + '\n');
                clientCopy.writer.flush();
            }
        }
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null) { //TODO blocked thread, what kind different solution have
                System.out.println(login + " > " + message);
                sendMessageToAllClients(message);
            }
            System.out.println(login + " disconnected");
            synchronized (clientCopies) {
                clientCopies.remove(this);  // TODO garbage collector clean the memory from this object?
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}