package main.src.cmd;

import main.src.chat.ChatRooms;
import main.src.chat.Room;
import main.src.user.User;

import java.io.PrintWriter;
import java.util.Objects;

public class RoomCreationCommand implements Command{
    private final String[] roomInfo;
    private final User user;
    private final PrintWriter writer;

    public RoomCreationCommand(String[] roomInfo, User user, PrintWriter writer) {
        this.roomInfo = roomInfo;
        this.user = user;
        this.writer = writer;
    }

    @Override
    public void execute() {
        if(user.getCurrentRoom() == null && user.getUserName() != null && roomInfo.length == 2){
            /*
                verify if the user has a username
                verify if the room name already exist
             */
            String status = String.valueOf
                    (Objects.equals(roomInfo[0], "/public") ? "public" : "private");
            /*
                create the room and affected to the Room's list
             */
            ChatRooms.getInstance().addRoom(new Room(roomInfo[1],status,user));

            // update the user current room
            user.setCurrentRoom(roomInfo[1]);

            System.out.println(roomInfo[1] +" room was created");

            writer.println(roomInfo[1] +" room joined.");
            return;
        }
        writer.println("not authorized.");
    }
}
