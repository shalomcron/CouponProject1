package dao.couponsCustomers;

import exceptions.JDBCException;

public interface CouponsCustomersDAO {
    void addCoupon(int customerId, int couponId) throws JDBCException;
    void getCouponsCustomer(int customerId) throws JDBCException;
    public void deleteCoupon(int customerId, int couponId);
}
