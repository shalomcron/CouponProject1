package exceptions;

public class CouponException extends Exception {
    public CouponException(CouponMsg couponMsg) {
        super(couponMsg.getMsg());
    }
}
