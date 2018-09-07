package ru.innopolis.stc12.sockets.Chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final Integer SERVER_PORT = 4999;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", SERVER_PORT);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            ClientListener clientListener = new ClientListener(bufferedReader); //TODO that right?
            clientListener.start();

            Scanner scanner = new Scanner(System.in);

            String message;
            while ((message = scanner.nextLine()).compareTo("close") != 0) {
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

            System.out.println("disconnected");
            socket.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
