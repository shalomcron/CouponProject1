package dao.couponsCustomers;

import beans.coupone.Coupon;
import beans.couponsCustomer.CouponsCustomer;
import db.JDBCUtils;
import db.ResultsUtils;
import exceptions.JDBCException;

import java.util.ArrayList;
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


    private static final String QUERY_GET_ALL = "SELECT * FROM `coupone-bhp-386`.coupons_customers;";
    private static final String QUERY_GET_ONE_PURCHASE = "SELECT * FROM `coupone-bhp-386`.coupons_customers " +
            "WHERE ID_CUSTOMER=? AND ID_COUPON=?";

    private static final String QUERY_COUPON_WAS_ALREADY_PURCHASED = "select exists " +
            "(SELECT * FROM `coupone-bhp-386`.coupons_customers WHERE ID_CUSTOMER=? AND ID_COUPON=?) as RES;";

    private static final String QUERY_DELETE_COUPON_CUSTOMER_PURCASE = "DELETE FROM `coupone-bhp-386`.coupons_customers " +
            " WHERE ID_CUSTOMER=? AND ID_COUPON=?";
    private static final String QUERY_DELETE_CUSTOMER_PURCASE = "DELETE FROM `coupone-bhp-386`.coupons_customers " +
            " WHERE ID_CUSTOMER=?";
    private static final String QUERY_DELETE_COUPON_PURCASE = "DELETE FROM `coupone-bhp-386`.coupons_customers " +
            " WHERE ID_CUSTOMER=?";

    private static final String QUERY_GET_ALL_PURCHASED = "SELECT * FROM" +
            " `coupone-bhp-386`.coupons_customers as cc," +
            " `coupone-bhp-386`.coupons as c" +
            " where cc.ID_COUPON = c.ID and ID_CUSTOMER = ?;";

    private static final String QUERY_GET_ALL_PURCHASED_CATEGORY = "SELECT * FROM" +
            " `coupone-bhp-386`.coupons_customers as cc," +
            " `coupone-bhp-386`.coupons as c" +
            " where cc.ID_COUPON = c.ID and ID_CUSTOMER=? AND c.ID_CATEGORY = ? ;";

    @Override
    public void purchaseCoupon(CouponsCustomer couponsCustomer) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponsCustomer.getCustomerID());
        params.put(2, couponsCustomer.getCouponId());
        JDBCUtils.executeQuery(QUERY_ADD_COUPON_PURCHASE, params);
    }

    @Override
    public List<CouponsCustomer> getAllPurchases() throws JDBCException {
        List<CouponsCustomer> results = new ArrayList<>();
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL);
        for (Map<String, Object> object : rows) {
            results.add(ResultsUtils.couponsCustomerFromRow(object));
        }
        return results;
    }

    @Override
    public CouponsCustomer getSinglePurchase(int couponId, int customerId) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponId);
        params.put(2, customerId);
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE_PURCHASE, params);
        return rows.size() > 0 ? ResultsUtils.couponsCustomerFromRow(rows.get(0)) : null;
    }

    @Override
    public void deleteCouponCustomerPurchase(int couponId, int customerId) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponId);
        params.put(2, customerId);
        JDBCUtils.executeQuery(QUERY_DELETE_COUPON_CUSTOMER_PURCASE, params);
    }

    @Override
    public void deleteCustomerPurchase(int customerId) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        JDBCUtils.executeQuery(QUERY_DELETE_CUSTOMER_PURCASE, params);
    }

    @Override
    public void deleteCouponCustomerPurchase(int couponId) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponId);
        JDBCUtils.executeQuery(QUERY_DELETE_COUPON_PURCASE, params);
    }

    @Override
    public boolean isCouponWasPurchased(int customerId, int couponId) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        params.put(2, couponId);
        return ResultsUtils.isExistFromRow(JDBCUtils.executeQueryWithResults(QUERY_COUPON_WAS_ALREADY_PURCHASED, params).get(0));
    }

    @Override
    public List<Coupon> getAllPurchases(int customerId) throws JDBCException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_PURCHASED, params);
        for (Map<String, Object> object : rows) {
            results.add(ResultsUtils.couponFromRow(object));
        }
        return results;
    }

    @Override
    public Iterable<Coupon> getPurchasedCoupons(int customerId, int categoryId) throws JDBCException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        params.put(2, categoryId);
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_PURCHASED_CATEGORY, params);
        for (Map<String, Object> object : rows) {
            results.add(ResultsUtils.couponFromRow(object));
        }
        return results;
    }
}
