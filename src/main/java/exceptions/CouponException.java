package exceptions;

public class CouponException extends Exception {
    public CouponException(CouponMsg couponMsg) {
        super(couponMsg.getMsg());
    }

    public CouponException(PurchaseCouponMsg couponMsg) {
        super(couponMsg.getMsg());
    }
}
