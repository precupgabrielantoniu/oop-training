package exceptions;

public class OfferNotFoundException extends RuntimeException{
    public OfferNotFoundException(){
        super();
    }
    public OfferNotFoundException(String s){
        super(s);
    }
}
