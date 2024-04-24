package fr.esgi.customer.exception;

public class MissingCustomerId extends RuntimeException {

    public MissingCustomerId(String message) {
        super(message);
    }
}
