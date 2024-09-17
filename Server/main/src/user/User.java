package main.src.user;

import java.io.IOException;
import java.net.Socket;

public class User {

    private String userName;
    private Socket userConnection ;
    private String currentRoom;

    public User(Socket connectionSocket){
        this.userConnection = connectionSocket;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setUserConnection(Socket userConnection) {
        this.userConnection = userConnection;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public Socket getUserConnection() {
        return userConnection;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public boolean closeConnection(){
        try {
            this.userConnection.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

