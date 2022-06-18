package facade.clients;

import beans.cliens.Customer;
import beans.coupone.Coupon;
import beans.couponsCustomer.CouponsCustomer;
import exceptions.CouponException;
import exceptions.JDBCException;
import exceptions.PurchaseCouponMsg;

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
        if (couponsCustomersDAO.couponWasPurchased(getCustomerId(), couponId)) {
            throw new CouponException(PurchaseCouponMsg.COUPON_WAS_ALREADY_PURCHASED);
        }
        if (coupon == null) {
            throw new CouponException(PurchaseCouponMsg.NO_COUPON_EXIST);
        }
        if (coupon.getAmount() == 0) {
            throw new CouponException(PurchaseCouponMsg.NO_COUPON_LEFT);
        }
        LocalDate endDate = coupon.getEndDate().toLocalDate();
        LocalDate now = LocalDate.now();
        if (endDate.isBefore(now)) {
            throw new CouponException(PurchaseCouponMsg.COUPON_EXPIRED);
        }
        couponsCustomersDAO.purchaseCoupon(new CouponsCustomer(getCustomerId(), couponId));
        coupon.setAmount(coupon.getAmount() -1);
        couponDAO.update(coupon.getId(), coupon);
    }
}
