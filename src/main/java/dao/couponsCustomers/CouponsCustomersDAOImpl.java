package dao.couponsCustomers;

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
    private static final String QUERY_UPDATE = "";
    private static final String QUERY_DELETE = "DELETE FROM `coupone-bhp-386`.coupons_customers " +
            " WHERE ID_CUSTOMER=? AND ID_COUPON=?";

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
    public void deletePurchase(int couponId, int customerId) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponId);
        params.put(2, customerId);
        JDBCUtils.executeQuery(QUERY_DELETE, params);
    }
}
