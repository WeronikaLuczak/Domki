package pl.wluczak.projektdomki.exception;

public class OperationNotAllowedException extends RuntimeException{

    public OperationNotAllowedException(String message) {
        super(message);
    }
}
