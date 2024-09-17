package main.src.cmd;

import main.src.chat.ChatRooms;
import main.src.chat.Room;
import main.src.user.User;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class ListUsersCommand implements Command{

    private final User user;
    private final PrintWriter writer;

    public ListUsersCommand(User user, PrintWriter writer){
        this.user = user;
        this.writer = writer;
    }
    @Override
    public void execute() {
        List<User> users;
        if(user.getCurrentRoom() == null){
            users = ChatRooms.getInstance().getGlobalUsers();
            users.forEach(u -> writer.println(u.getUserName()));
        }else{
            Optional<Room> room = ChatRooms.getInstance().getRooms()
                    .stream()
                    .filter(r -> r.getRoomName().equals(user.getCurrentRoom()))
                    .findAny();

            users = room.map(Room::getRoomUsers).orElse(null);
            if(users != null) users.forEach(u -> writer.println(u.getUserName()));
        }
    }
}
