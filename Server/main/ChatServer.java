package main;

import main.src.user.UserHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    private static final int port = 3334;

    public static void main(String[] args) {

        try(ServerSocket server = new ServerSocket(port)) {
            System.out.println("ChatServer running...");

            while(true){
                Socket connectionSocket = server.accept();
                System.out.println("New client connected with IP :" + connectionSocket.getInetAddress());

                new UserHandler(connectionSocket).start();
            }
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }

    }
}
