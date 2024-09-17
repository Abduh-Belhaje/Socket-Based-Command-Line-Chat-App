package main.src.cmd;

import main.src.user.User;

import java.io.IOException;
import java.io.PrintWriter;

public class QuitCommand implements Command{

    private final User user;
    private final PrintWriter writer;

    public QuitCommand(User user, PrintWriter writer) {
        this.user = user;
        this.writer = writer;
    }

    @Override
    public void execute() {
        writer.println("closing the connection...");
        if(user.closeConnection()){
            Thread.currentThread().interrupt();
        }
        writer.println("problem accused when closing connection.");
        /*
            if the user was in a room I will broadcast the message
            if not I will just log it
         */
    }
}
