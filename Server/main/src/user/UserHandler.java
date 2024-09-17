package main.src.user;

import main.src.cmd.*;

import java.io.*;
import java.net.Socket;

public class UserHandler extends Thread {
    private final User user;
    public Command commandLine;

    public UserHandler(Socket connectionSocket) {
        this.user = new User(connectionSocket);
    }

    @Override
    public void run() {
        try (InputStream input = user.getUserConnection().getInputStream();
             OutputStream output = user.getUserConnection().getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             PrintWriter writer = new PrintWriter(output, true)) {

            // Inform the user by the authorized commands
            initialDescription(writer);

            // Read messages and handle input
            String message;
            while ((message = reader.readLine()) != null) {
                handleInput(message,writer);
            }

        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
        }finally {
            if(user.getUserConnection().isClosed()){
                System.out.println(user.getUserName() + " closed the connection");
                Thread.currentThread().interrupt();
            }
        }
    }

    private void handleInput(String message,PrintWriter writer) {
        String[] params = message.split(" ");
        switch (params[0]){
            case "/login" :
                commandLine = new UserAddCommand(user,params[1], writer);
                commandLine.execute();
                break;
            case "/users" :
                commandLine = new ListUsersCommand(user,writer);
                commandLine.execute();
                break;
            case "/rooms":
                commandLine = new ListRoomsCommand(writer,user);
                commandLine.execute();
                break;
            case "/public", "/private":
                commandLine = new RoomCreationCommand(params,user,writer);
                commandLine.execute();
                break;
            case "/join":
                commandLine = new JoinCommand(params[1],user,writer);
                commandLine.execute();
                break;
            case "/invite":
                commandLine = new InviteCommand(user,writer,params[1]);
                commandLine.execute();
                break;
            case "/whoami":
                commandLine = new WhoamiCommand(user,writer);
                commandLine.execute();
                break;
            case "/whereami":
                commandLine = new WhereamiCommand(user,writer);
                commandLine.execute();
                break;
            case "/leave":
                commandLine = new LeaveRoomCommand(writer,user);
                commandLine.execute();
                break;
            case "/quit" :
                commandLine = new QuitCommand(user,writer);
                commandLine.execute();
                break;
            default:
                commandLine = new SendCommand(message,user);
                commandLine.execute();
                break;
        }
    }

    private void initialDescription(PrintWriter writer){

        String line = "*".repeat(20);
        writer.println("\n\n" +line + " Description " + line + "\n");

        writer.println("/login username : in order to start sending messages");
        writer.println("/users : list users based on scope");
        writer.println("/rooms : list all rooms");
        writer.println("/join room_name : if you want to join a specific room");
        writer.println("/public room_name : to create a public room");
        writer.println("/private room_name : to create a private room");
        writer.println("/invite username : invite people to your room");
        writer.println("/whoami : to show the username");
        writer.println("/whereami username : to show your current room");
        writer.println("/leave : in order to leave the current room");
        writer.println("/quit : to close the connection\n\n");

    }
}
