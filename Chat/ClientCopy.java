package ru.innopolis.stc12.sockets.Chat;

import java.io.*;
import java.net.Socket;

class ClientCopy extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String message;
    private String login;

    public ClientCopy(Socket socket, String login) throws IOException {
        this.socket = socket;
        this.login = login;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    public void sendMessage(String text) throws IOException {
        writer.write(text + '\n');
        writer.flush();
    }

    public void sendMessageToAllClient(String text) throws IOException {
        for (ClientCopy clientCopy : Server.clientCopies) {
            if (clientCopy == this) {
                continue;
            }
            clientCopy.writer.write(login + " > " + text + '\n');
            clientCopy.writer.flush();
        }
    }

    @Override
    public void run() {
        try {
            while ((message = reader.readLine()) != null) {
                System.out.println(login + " > " + message);
                sendMessageToAllClient(message);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}