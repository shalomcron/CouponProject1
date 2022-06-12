package exceptions;

public class CustomerException extends Exception {
    public CustomerException(CustomerMsg customerMsg) {
        super(customerMsg.getMsg());
    }
}
