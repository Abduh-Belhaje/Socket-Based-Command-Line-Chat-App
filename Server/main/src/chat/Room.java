package main.src.chat;

import main.src.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {

    private String roomName;
    private final List<User> roomUsers;
    private Map<String,String> messages;
    private final List<User> invited;
    private String status;

    public Room(String roomName, String status, User user) {
        this.roomName = roomName;
        this.invited = new ArrayList<>();
        this.roomUsers = new ArrayList<>();
        this.messages = new HashMap<>();
        this.status = status;
        this.addUser(user);
        this.addInvitedUser(user);
    }


    public void addUser(User user) {
        roomUsers.add(user);
    }

    public void addInvitedUser(User user) {
        invited.add(user);
    }

    public void removeUser(User user){
        roomUsers.remove(user);
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getRoomUsers() {
        return roomUsers;
    }

    public List<User> getInvited() {
        return invited;
    }
}
