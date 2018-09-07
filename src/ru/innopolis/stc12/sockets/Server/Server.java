package ru.innopolis.stc12.sockets.Server;

import ru.innopolis.stc12.sockets.Client.Client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static Integer SERVER_PORT = 4999;

    public static void main(String[] args) {
        ServerListener serverListener = new ServerListener(SERVER_PORT);
        serverListener.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Socket socket = new Socket("127.0.0.1", Client.CLIENT_PORT);
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
