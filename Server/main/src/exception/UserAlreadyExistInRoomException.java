package main.src.exception;

public class UserAlreadyExistInRoomException extends Exception{

    public UserAlreadyExistInRoomException(String mssg){
        super(mssg);
    }
}
