package dao.couponsCustomers;

// extends DAO<CouponsCustomer, Integer>

import beans.couponsCustomer.CouponsCustomer;
import exceptions.JDBCException;

import java.util.List;

public interface CouponsCustomersDAO {

    void purchaseCoupon(CouponsCustomer couponsCustomer) throws JDBCException;

    List<CouponsCustomer> getAllPurchases() throws JDBCException;

    CouponsCustomer getSinglePurchase(int couponId, int customerId) throws JDBCException;

    void deletePurchase(int couponId, int customerId) throws JDBCException;

    boolean couponWasPurchased(int customerId, int couponId) throws JDBCException;
}
