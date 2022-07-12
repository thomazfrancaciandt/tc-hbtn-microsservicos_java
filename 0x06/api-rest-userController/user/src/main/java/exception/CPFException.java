package exception;

public class CPFException extends RuntimeException{

    private String message;

    public CPFException(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}