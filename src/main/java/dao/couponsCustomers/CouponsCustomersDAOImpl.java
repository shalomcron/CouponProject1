package dao.couponsCustomers;

import beans.couponsCustomer.CouponsCustomer;
import db.JDBCUtils;
import exceptions.JDBCException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponsCustomersDAOImpl implements CouponsCustomersDAO {
    private static final CouponsCustomersDAOImpl instance = new CouponsCustomersDAOImpl();

    public static CouponsCustomersDAOImpl getInstance() {
        return instance;
    }

    private CouponsCustomersDAOImpl() {
    }

    private static final String QUERY_ADD_COUPON_PURCHASE = "INSERT INTO `coupone-bhp-386`.`coupons_customers` " +
            "(`ID_CUSTOMER`, `ID_COUPON`) VALUES (?, ?);";
    private static final String DELETE_ADD_COUPON_PURCHASE = "DELETE FROM `coupone-bhp-386`.`coupons_customers` " +
            "WHERE (`ID_CUSTOMER` = ?) and (`ID_COUPON` = ?)";

//    private static final String QUERY_INSERT = "INSERT INTO `coupone-bhp-386`.`coupons_customers` " +
//            "(`ID_CUSTOMER`, `ID_COUPON`) VALUES (?, ?);";

    private static final String QUERY_GET_ALL = "SELECT * FROM `coupone-bhp-386`.coupons_customers;";
    private static final String QUERY_GET_ONE = "";
    private static final String QUERY_UPDATE = "";
    private static final String QUERY_DELETE = "";

    @Override
    public void add(CouponsCustomer couponsCustomer) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponsCustomer.getCustomerID());
        params.put(2, couponsCustomer.getCouponId());
        JDBCUtils.executeQuery(QUERY_ADD_COUPON_PURCHASE, params);
    }

    @Override
    public List<CouponsCustomer> getAll() throws JDBCException {
        return null;
    }

    @Override
    public CouponsCustomer getSingle(Integer integer) throws JDBCException {
        return null;
    }

    @Override
    public void update(Integer integer, CouponsCustomer couponsCustomer) throws JDBCException {

    }

    @Override
    public void delete(Integer integer) throws JDBCException {

    }

    // boolean hasPurchaseCoupon(int couponId, int customerId);
//    @Override
//    public boolean hasPurchaseCoupon(int couponId, int customerId) {
//        return false;
//    }
/*
    @Override
    public void addCouponPurchase(int customerId, int couponId) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        params.put(2, couponId);
        JDBCUtils.executeQuery(QUERY_ADD_COUPON_PURCHASE, params);
    }

    @Override
    public void deleteCouponPurchase(int customerId, int couponId) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        params.put(2, couponId);
        JDBCUtils.executeQuery(DELETE_ADD_COUPON_PURCHASE, params);
    }

     public static void addCouponPurchase() throws JDBCException {
        System.out.println("addCouponPurchase");
        couponDAO.addCouponPurchase(1, 1);
        couponDAO.addCouponPurchase(2, 2);
        couponDAO.addCouponPurchase(3, 3);
        couponDAO.addCouponPurchase(4, 4);
        couponDAO.addCouponPurchase(5, 5);
    }

    private static void deleteCouponPurchase() throws JDBCException {
        System.out.println("deleteCouponPurchase");
        couponDAO.deleteCouponPurchase(1, 1);
        couponDAO.deleteCouponPurchase(5, 5);
    }
*/
}
