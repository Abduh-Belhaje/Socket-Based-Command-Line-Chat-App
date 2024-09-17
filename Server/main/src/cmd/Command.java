package main.src.cmd;

import main.src.exception.CommandExecutionException;
import main.src.exception.UserAlreadyExistInRoomException;
import main.src.user.User;

public interface Command {

    void execute();
}