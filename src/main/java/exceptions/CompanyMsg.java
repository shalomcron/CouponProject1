package exceptions;

public enum CompanyMsg {
    COMPANY_NOT_EXIST("COMPANY NOT EXIST"),
    COMPANY_NAME_ALREADY_EXIST("COMPANY NAME ALREADY EXIST"),
    COMPANY_EMAIL_ALREADY_EXIST("COMPANY EMAIL ALREADY EXIST"),
    ;

    private final String msg;

    CompanyMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
