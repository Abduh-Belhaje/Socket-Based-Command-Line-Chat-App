package main.src.cmd;

import main.src.chat.ChatRooms;
import main.src.exception.CommandExecutionException;
import main.src.user.User;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class UserAddCommand implements Command{

    private final User user;
    private final String username;
    private final PrintWriter writer;

    public UserAddCommand(User user , String username, PrintWriter writer){
        this.user = user;
        this.username = username;
        this.writer = writer;
    }

    @Override
    public void execute() {
        List<User> serverUsers = ChatRooms.getInstance().getGlobalUsers();
        if(serverUsers != null){
            Optional<User> result = serverUsers.stream()
                    .filter(u -> u.getUserName().equals(username))
                    .findAny();

            if(result.isPresent()){
                writer.println("Username already exists !");
                return;
            }
        }

        user.setUserName(username);
        ChatRooms.getInstance().addUser(user);

        System.out.println(username + " is added successfully");
    }
}
