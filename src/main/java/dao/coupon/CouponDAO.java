package dao.coupon;

import beans.coupone.Coupon;
import dao.DAO;
import exceptions.JDBCException;

import java.util.List;

public interface CouponDAO extends DAO<Coupon, Integer> {
    boolean isExistByTitle(int companyId, String title) throws JDBCException;

    List<Coupon> getAllCompanyCoupons(int companyId) throws JDBCException;

    List<Coupon> getAllCompanyCoupons(int companyId, int categoryId) throws JDBCException;

    List<Coupon> getAllCompanyCoupons(int companyId, double maxPrice) throws JDBCException;

    void deleteExpiredCoupons() throws JDBCException;

    Coupon getCouponCompany(int couponId, int companyId) throws JDBCException;

    void reduceAmountCouponCompany(int couponId, int companyId, Coupon coupon) throws JDBCException;

    void deleteCompanyCoupons(int companyId) throws JDBCException;
}
