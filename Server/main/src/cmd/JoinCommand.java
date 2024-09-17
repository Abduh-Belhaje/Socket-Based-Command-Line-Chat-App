package main.src.cmd;

import main.src.chat.ChatRooms;
import main.src.chat.Room;
import main.src.user.User;
import main.src.utils.SendMessage;

import java.io.PrintWriter;
import java.util.Optional;

public class JoinCommand implements Command{
    private final String roomName;
    private final User user;
    private final PrintWriter writer;

    public JoinCommand(String roomName, User user, PrintWriter writer) {
        this.roomName = roomName;
        this.user = user;
        this.writer = writer;
    }

    @Override
    public void execute(){
        /*
            check if the user has a username
         */
        if(user.getUserName() == null){
            writer.println("log first !");
            return;
        }
        if(user.getCurrentRoom() != null){
            writer.println("leave this room first.");
        }
        Optional<Room> room = ChatRooms.getInstance().getRooms()
                                        .stream()
                                        .filter(r -> r.getRoomName().equals(roomName))
                                        .findAny();


        if(room.isPresent()){
            if(room.get().getStatus().equals("private") ){
                Optional<User> result = room.get().getInvited().stream()
                        .filter(u -> u.getUserName().equals(user.getUserName()))
                        .findAny();
                if(result.isEmpty()){
                    writer.println("you're not invited.");
                    return;
                }
            }
            user.setCurrentRoom(roomName);
            room.get().addUser(user);
            // broadcast the message
            String msg = user.getUserName() + " join the room.";
            SendMessage.broadcatMessage(user,msg);

            writer.println(roomName +" room joined.");
            return;
        }

        writer.println("room name doesn't exist !");
    }
}
