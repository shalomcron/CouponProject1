package exceptions;

public class JDBCException extends Exception {

    public JDBCException(LayerMsg layerMsg, String message) {
        super("Exception occurred in " + layerMsg.name() + " layer. \n  msg: " + message);
    }
}
