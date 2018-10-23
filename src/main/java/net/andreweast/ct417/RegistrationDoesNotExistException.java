package net.andreweast.ct417;

public class RegistrationDoesNotExistException extends Exception {
    public RegistrationDoesNotExistException() {
    }

    public RegistrationDoesNotExistException(String message) {
        super(message);
    }
}
