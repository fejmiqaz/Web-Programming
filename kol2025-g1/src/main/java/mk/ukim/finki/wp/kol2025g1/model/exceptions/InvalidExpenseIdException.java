package mk.ukim.finki.wp.kol2025g1.model.exceptions;

public class InvalidExpenseIdException extends RuntimeException {
    private String message;

    public InvalidExpenseIdException(String message) {
        this.message = message;
    }
}
