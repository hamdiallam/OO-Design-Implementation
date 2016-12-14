package Exceptions;

/**
 * This exception will be thrown during
 * the checkout procedure
 */
public class CheckoutException extends Exception {
    public CheckoutException(String message){
        super(message);
    }
}
