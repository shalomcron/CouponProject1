package exceptions;

public enum CouponMsg {
    COUPON_TITLE_ALREADY_EXIST("COUPON TITLE ALREADY EXIST"),
    COUPON_ID_NOT_EDITABLE("COUPON ID NOT EDITABLE"),
    COUPON_COMPANY_ID_NOT_EDITABLE("COUPON COMPANY ID NOT EDITABLE");

    private final String msg;

    public String getMsg() {
        return msg;
    }

    CouponMsg(String msg) {
        this.msg = msg;
    }
}
