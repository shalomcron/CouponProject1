package dao.couponsCustomers;

// extends DAO<CouponsCustomer, Integer>

import beans.coupone.Coupon;
import beans.couponsCustomer.CouponsCustomer;
import exceptions.JDBCException;

import java.util.List;

public interface CouponsCustomersDAO {

    void purchaseCoupon(CouponsCustomer couponsCustomer) throws JDBCException;

    List<CouponsCustomer> getAllPurchases() throws JDBCException;

    CouponsCustomer getSinglePurchase(int couponId, int customerId) throws JDBCException;

    void deletePurchase(int couponId, int customerId) throws JDBCException;

    void deletePurchase(int couponId) throws JDBCException;
    void deleteCustomerPurchase(int customerId) throws JDBCException;

    boolean isCouponWasPurchased(int customerId, int couponId) throws JDBCException;

    List<Coupon> getAllPurchases(int customerId) throws JDBCException;

    Iterable<Coupon> getPurchasedCoupons(int customerId, int customerId1) throws JDBCException;

}
