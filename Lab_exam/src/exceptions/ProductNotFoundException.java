package exceptions;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(){
        super();
    }

    public ProductNotFoundException(String s) {
        super(s);
    }
}
