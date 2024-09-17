package main.src.cmd;

import main.src.chat.ChatRooms;
import main.src.chat.Room;
import main.src.user.User;

import java.io.PrintWriter;
import java.util.List;

public class ListRoomsCommand implements Command{
    private final PrintWriter writer;
    private final User user;

    public ListRoomsCommand(PrintWriter writer, User user) {
        this.writer = writer;
        this.user = user;
    }

    @Override
    public void execute() {
        if(user.getCurrentRoom() == null){
            List<Room> rooms = ChatRooms.getInstance().getRooms();
            if (rooms != null) rooms.forEach(r -> writer.println(r.getRoomName() + " : " + r.getStatus()));
            return;
        }
        writer.println("rooms listing disabled in this scope...");
    }
}
