package main.src.cmd;

import main.src.chat.ChatRooms;
import main.src.chat.Room;
import main.src.user.User;
import main.src.utils.SendMessage;

import java.io.PrintWriter;
import java.util.Optional;

public class InviteCommand implements Command{

    private final User user;
    private final PrintWriter writer;
    private final String invitedName;

    public InviteCommand(User user, PrintWriter writer, String invitedName) {
        this.user = user;
        this.writer = writer;
        this.invitedName = invitedName;
    }

    @Override
    public void execute() {
        // ensure that the user is already in a room
        if(user.getCurrentRoom() == null){
            writer.println("no room joined to invite people.");
            return;
        }
        // check if the invited username exist in the server
        Optional<User> invitedUser = ChatRooms.getInstance().getGlobalUsers()
                .stream()
                .filter(u -> u.getUserName().equals(invitedName))
                .findAny();

        if(invitedUser.isPresent()){
            String sing = user.getCurrentRoom() + " room invitation from " + user.getUserName();
            SendMessage.sendPrivateMessage(invitedUser.get(),sing);

            Optional<Room> room = ChatRooms.getInstance().getRooms()
                    .stream()
                    .filter(r -> r.getRoomName().equals(user.getCurrentRoom()))
                    .findAny();

            room.ifPresent(value -> {
                if(value.getStatus().equals("private")) value.addInvitedUser(invitedUser.get());
            });
        }
    }
}
