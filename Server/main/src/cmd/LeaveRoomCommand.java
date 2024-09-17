package main.src.cmd;

import main.src.chat.ChatRooms;
import main.src.chat.Room;
import main.src.user.User;
import main.src.utils.SendMessage;

import java.io.PrintWriter;
import java.util.Optional;

public class LeaveRoomCommand implements Command{

    private final PrintWriter writer;
    private final User user;

    public LeaveRoomCommand(PrintWriter writer, User user) {
        this.writer = writer;
        this.user = user;
    }

    @Override
    public void execute() {
        /*
            1 _ find room by name
            2 _ delete the user from room
         */
        if(user.getCurrentRoom() != null){
            Optional<Room> room = ChatRooms.getInstance().getRooms()
                    .stream()
                    .filter(r -> r.getRoomName().equals(user.getCurrentRoom()))
                    .findAny();

            // delete the user from user's list
            room.ifPresent(value -> value.removeUser(user));

            // broadcast the message
            String msg = user.getUserName() + " left the room.";
            SendMessage.broadcatMessage(user,msg);

            // set the current room name to null
            user.setCurrentRoom(null);

            writer.println("quit room .");
            return;
        }
        writer.println("no room joined .");
    }
}
