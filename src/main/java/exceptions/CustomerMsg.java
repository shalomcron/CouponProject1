package exceptions;

public enum CustomerMsg {
    CUSTOMER_EMAIL_EXIST("CUSTOMER_EMAIL_EXIST"),
    CUSTOMER_ID_ALREADY_EXIST("CUSTOMER ID ALREADY EXIST"),
    CUSTOMER_NO_EXIST("CUSTOMER NO EXIST"),
    ;

    public String getMsg() {
        return msg;
    }

    private final String msg;

    CustomerMsg(String msg) {
        this.msg = msg;
    }
}
