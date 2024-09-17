package main.src.chat;

import main.src.user.User;

import java.util.ArrayList;
import java.util.List;

/*
    Singleton class in order to create a shared Server data
 */
public class ChatRooms {

    private final List<User> globalUsers ;
    private final List<Room> rooms;
    private static ChatRooms chatRooms;

    private ChatRooms(){
        this.globalUsers = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public static ChatRooms getInstance(){
        if(chatRooms == null){
            chatRooms = new ChatRooms();
        }
        return chatRooms;
    }

    public void addUser(User user){
       globalUsers.add(user);
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public List<User> getGlobalUsers() {
        return globalUsers;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
