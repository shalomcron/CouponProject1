package exceptions;

public enum PurchaseCouponMsg {
    NO_COUPON_LEFT("NO COUPON LEFT"),
    NO_COUPON_EXIST("NO COUPON EXIST"),
    COUPON_WAS_ALREADY_PURCHASED("COUPON WAS ALREADY PURCHASED"),
    COUPON_EXPIRED("COUPON EXPIRED"),
    ;

    private final String msg;

    public String getMsg() {
        return msg;
    }

    PurchaseCouponMsg(String msg) {
        this.msg = msg;
    }
}
