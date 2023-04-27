package exceptions;

public class ValidationFailedException extends RuntimeException{
    public ValidationFailedException(){
        super();
    }
    public ValidationFailedException(String s){
        super(s);
    }

}
