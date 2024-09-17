package main.src.cmd;

import main.src.user.User;

import java.io.PrintWriter;

public class WhereamiCommand implements Command{

    private final User user;
    private final PrintWriter writer;

    public WhereamiCommand(User user, PrintWriter writer) {
        this.user = user;
        this.writer = writer;
    }

    @Override
    public void execute() {
        if(user.getCurrentRoom() == null){
            writer.println("no room joined !");
            return;
        }
        writer.println(user.getCurrentRoom());
    }
}
