package restApplication.rest.exceptions;

public class RequestException extends RuntimeException{

    public RequestException(String message){
        super(message);
    }

}
