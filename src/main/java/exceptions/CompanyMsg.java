package exceptions;

public enum CompanyMsg {
    COMPANY_NOT_EXIST("COMPANY NOT EXIST"),
    COMPANY_NAME_ALREADY_EXIST("COMPANY NAME ALREADY EXIST"),
    COMPANY_EMAIL_ALREADY_EXIST("COMPANY EMAIL ALREADY EXIST"),
    COMPANY_ID_CANNOT_BE_UPDATED("COMPANY ID CANNOT BE UPDATED"),
    COMPANY_NAME_CANNOT_BE_UPDATED("COMPANY NAME CANNOT BE UPDATED");

    private final String msg;

    CompanyMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
