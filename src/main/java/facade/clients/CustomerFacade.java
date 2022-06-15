package facade.clients;

import beans.cliens.Customer;
import beans.coupone.Coupon;
import exceptions.CouponException;
import exceptions.CouponMsg;
import exceptions.JDBCException;

import java.time.LocalDate;

public class CustomerFacade extends ClientFacade {
    private int customerId;

    @Override
    public boolean login(String email, String password) {
        try {
            Customer customer = customerDAO.getSingle(email, password);
            if (customer != null) {
                this.customerId = customer.getId();
                return true;
            }
        } catch (JDBCException e) {
            System.out.println("login -" + e);
        }
        return false;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void purchaseCoupon(int couponId) throws JDBCException, CouponException {
        Coupon coupon = couponDAO.getSingle(couponId);
        System.out.println("purchaseCoupon coupon " + coupon);
        if (coupon.getAmount() == 0) {
            throw new CouponException(CouponMsg.NO_COUPON_LEFT);
        }
        LocalDate endDate = coupon.getEndDate().toLocalDate();
        LocalDate now = LocalDate.now();
        if (endDate.isBefore(now)) {
            throw new CouponException(CouponMsg.COUPON_EXPIRED);
        }
//        if (customerDAO.hasPurchaseCoupon(couponId, getCustomerId())) {
//
//        }
    }
}
