package main.src.cmd;

import main.src.user.User;

import java.io.PrintWriter;

public class WhoamiCommand implements Command{

    private final User user;
    private final PrintWriter writer;

    public WhoamiCommand(User user, PrintWriter writer) {
        this.user = user;
        this.writer = writer;
    }

    @Override
    public void execute() {
        if(user.getUserName() == null){
            writer.println("log first !");
            return;
        }
        writer.println(user.getUserName());
    }
}
