package dao.coupon;

import beans.Coupon;
import dao.DAO;
import exceptions.JDBCException;

public interface CouponDAO extends DAO<Coupon, Integer> {
    void addCouponPurchase(int customerId, int couponId) throws JDBCException;
    void deleteCouponPurchase(int customerId, int couponId) throws JDBCException;
}
