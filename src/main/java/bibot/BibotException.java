package bibot;

/**
 * Represents an exception encountered in the chatbot application.
 */
public class BibotException extends Exception {

    /**
     * Constructs a new <code>BibotException</code> representing an exception
     * encountered in the chatbot application.
     * 
     * @param message Description of the exception.
     */
    public BibotException(String message) {
        super(message);
    }
}
