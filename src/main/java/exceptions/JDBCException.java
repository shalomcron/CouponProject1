package exceptions;

public class JDBCException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public JDBCException(LayerMsg layerMsg, String message) {
        super("Exception occurred in " + layerMsg.name() + " layer. \n  msg: " + message);
    }
}
