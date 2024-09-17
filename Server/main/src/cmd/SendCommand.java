package main.src.cmd;

import main.src.user.User;
import main.src.utils.SendMessage;

import java.io.PrintWriter;

public class SendCommand implements Command{
    private final String message;
    private final User user;


    public SendCommand(String message, User user) {
        this.message = message;
        this.user = user;
    }

    @Override
    public void execute() {
        if(user.getCurrentRoom() != null && !message.isEmpty()){
            SendMessage.broadcatMessage(user, user.getUserName() +": "+message);
        }
    }
}
