package dao.couponsCustomers;


import db.JDBCUtils;
import exceptions.JDBCException;

import java.util.HashMap;
import java.util.Map;

public class CouponsCustomersDAOImpl implements CouponsCustomersDAO {
    private static final CouponsCustomersDAOImpl instance = new CouponsCustomersDAOImpl();
    private CouponsCustomersDAOImpl() {}
    public static CouponsCustomersDAOImpl getInstance() {
        return instance;
    }

    private static final String QUERY_INSERT = "INSERT INTO `coupone-bhp-386`.`coupons_customers` " +
            "(`ID_CUSTOMER`, `ID_COUPON`) VALUES (?, ?);";
    private static final String QUERY_GET_COUPONS_CUSTOMERS = "";

    @Override
    public void addCoupon(int customerId, int couponId) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        params.put(2, couponId);
        JDBCUtils.executeQuery(QUERY_INSERT, params);
    }

//    @Override
//    public void getCouponsCustomer(int customerId) throws JDBCException {
//        HashMap<Integer, Object> params = new HashMap<>();
//        Object res = JDBCUtils.executeQueryWithResults(QUERY_GET_COUPONS_CUSTOMERS, params);
//    }

//    @Override
//    public void deleteCoupon(int customerId, int couponId) {
//
//    }
}
