package exception;
public class UserIdException extends RuntimeException{

    private String message;

    public UserIdException(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}