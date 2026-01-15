package mk.ukim.finki.wp.kol2025g1.model.exceptions;

public class InvalidVendorIdException extends RuntimeException {
    private String message;

    public InvalidVendorIdException(String message) {
        super(message);
        this.message = message;
    }
}
