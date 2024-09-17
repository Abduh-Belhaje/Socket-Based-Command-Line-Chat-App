package main.src.exception;

public class InvalidUsernameException extends Exception{

    public InvalidUsernameException(String mssg){
        super(mssg);
    }
}
