package main.src.utils;

import main.src.chat.ChatRooms;
import main.src.chat.Room;
import main.src.user.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class SendMessage {

    public static void sendPrivateMessage(User reciepent,String message){
        try {
            PrintWriter out = new PrintWriter(reciepent.getUserConnection().getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void broadcatMessage(User user , String message){

        Optional<Room> room = ChatRooms.getInstance().getRooms()
                .stream()
                .filter(r -> r.getRoomName().equals(user.getCurrentRoom()))
                .findAny();

        room.ifPresent(value -> value.getRoomUsers().forEach(u -> {
            PrintWriter out = null;
            try {
                if(!u.getUserName().equals(user.getUserName())) {
                    out = new PrintWriter(u.getUserConnection().getOutputStream(), true);
                    out.println(message);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }));
    }
}
