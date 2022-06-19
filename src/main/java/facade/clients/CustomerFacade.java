package facade.clients;

import beans.cliens.Customer;
import beans.coupone.Coupon;
import beans.couponsCustomer.CouponsCustomer;
import exceptions.CouponException;
import exceptions.JDBCException;
import exceptions.PurchaseCouponMsg;

import java.time.LocalDate;
import java.util.List;

public class CustomerFacade extends ClientFacade {
    private int customerId;
    private String customerName;

    @Override
    public boolean login(String email, String password) {
        try {
            Customer customer = customerDAO.getSingle(email, password);
            if (customer != null) {
                this.customerId = customer.getId();
                this.customerName= customer.getFirstName();
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
    public String getCustomerName() {
        return this.customerName;
    }

    public void purchaseCoupon(int couponId) throws JDBCException, CouponException {
        Coupon coupon = couponDAO.getSingle(couponId);
        if (couponsCustomersDAO.isCouponWasPurchased(getCustomerId(), couponId)) {
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
    }

    public List<Coupon> getPurchasedCoupons(int customerId) throws JDBCException {
        return couponsCustomersDAO.getAllPurchases(customerId);
    }

    public Iterable<Coupon> getPurchasedCoupons(int customerId, int categoryId) throws JDBCException {
        return couponsCustomersDAO.getPurchasedCoupons(customerId, categoryId);
    }
}
