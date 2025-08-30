package jason.exception;


public class JasonException extends Exception {

    /**
     * Constructs a new JasonException with the specified error message.
     *
     * @param message The String message causing the exception
     */
    public JasonException(String message) {
        super(message);
    }
}