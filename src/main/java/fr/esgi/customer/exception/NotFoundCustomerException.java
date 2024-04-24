package fr.esgi.customer.exception;

public class NotFoundCustomerException extends RuntimeException {

    public NotFoundCustomerException(String message) {
        super(message);
    }
}
