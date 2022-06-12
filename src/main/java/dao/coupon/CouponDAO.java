package dao.coupon;

import beans.coupone.Category;
import beans.coupone.Coupon;
import dao.DAO;
import exceptions.JDBCException;

import java.util.List;

public interface CouponDAO extends DAO<Coupon, Integer> {
    void addCouponPurchase(int customerId, int couponId) throws JDBCException;
    void deleteCouponPurchase(int customerId, int couponId) throws JDBCException;
    boolean isExistByTitle(int companyId, String title) throws JDBCException;

    List<Coupon> getAllCompanyCoupons(int companyId) throws JDBCException;

    List<Coupon> getAllCategoryCoupons(int companyId, int categoryId) throws JDBCException;
}
