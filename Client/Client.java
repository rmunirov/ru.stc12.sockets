package ru.innopolis.stc12.sockets.Client;

import ru.innopolis.stc12.sockets.Server.Server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static Integer CLIENT_PORT = 4988;

    public static void main(String[] args) {
        ClientListener clientListener = new ClientListener();
        clientListener.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Socket socket = new Socket("127.0.0.1", Server.SERVER_PORT);
            OutputStreamWriter serverOutput = new OutputStreamWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            String message;
            while ((message = scanner.nextLine()) != "") {
                BufferedWriter bufferedWriter = new BufferedWriter(serverOutput);
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
