package net.andreweast.ct417;

public class DuplicateRegistrationException extends Exception {
    public DuplicateRegistrationException() {
    }

    public DuplicateRegistrationException(String message) {
        super(message);
    }
}
