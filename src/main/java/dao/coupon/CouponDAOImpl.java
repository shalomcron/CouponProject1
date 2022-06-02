package dao.coupon;

import beans.Coupon;
import dao.couponsCustomers.CouponsCustomersDAOImpl;
import db.JDBCUtils;
import db.ResultsUtils;
import exceptions.JDBCException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponDAOImpl implements CouponDAO {
    private static final CouponDAOImpl instance = new CouponDAOImpl();

    private static final CouponsCustomersDAOImpl couponsCustomersDAO = CouponsCustomersDAOImpl.getInstance();

    private static final String QUERY_INSERT = "INSERT INTO `coupone-bhp-386`.`coupons` " +
            "(`ID_COMPANY`, `ID_CATEGORY`, `TITLE`, `DESCRIPTION`, `DATE_START`, `DATE_END`, `AMOUNT`, `PRICE`, `IMAGE`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String QUERY_GET_ALL = "SELECT * FROM `coupone-bhp-386`.coupons";
    private static final String QUERY_GET_ONE = "SELECT * FROM `coupone-bhp-386`.coupons WHERE id=?";

    private static final String QUERY_UPDATE = "UPDATE `coupone-bhp-386`.`coupons` " +
            "SET `ID_COMPANY` = ?, `ID_CATEGORY` = ?, `TITLE` = ?, `DESCRIPTION` = ?, " +
            "`DATE_START` = ?, `DATE_END` = ?, `AMOUNT` = ?, `PRICE` = ?, `IMAGE` = ? WHERE (`ID` = ?);";
    private static final String QUERY_DELETE = "DELETE FROM `coupone-bhp-386`.`coupons` WHERE (`ID` = ?)";


    private CouponDAOImpl() {}

    public static CouponDAOImpl getInstance() {
        return instance;
    }

    @Override
    public void add(Coupon coupon) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getCompanyId());
        params.put(2, coupon.getCategoryId());
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, coupon.getStartDate());
        params.put(6, coupon.getEndDate());
        params.put(7, coupon.getAmount());
        params.put(8, coupon.getPrice());
        params.put(9, coupon.getImage());
        JDBCUtils.executeQuery(QUERY_INSERT, params);
    }

    @Override
    public List<Coupon> getAll() throws JDBCException {
        List<Coupon> results = new ArrayList<>();
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL);
        for (Map<String, Object> object : rows) {
            results.add(ResultsUtils.couponFromRow(object));
        }
        return results;
    }

    @Override
    public Coupon getSingle(Integer id) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE, params);
        return ResultsUtils.couponFromRow(rows.get(0));
    }

    @Override
    public void update(Integer id, Coupon coupon) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getCompanyId());
        params.put(2, coupon.getCategoryId());
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, coupon.getStartDate());
        params.put(6, coupon.getEndDate());
        params.put(7, coupon.getAmount());
        params.put(8, coupon.getPrice());
        params.put(9, coupon.getImage());
        params.put(10, id);
        JDBCUtils.executeQuery(QUERY_UPDATE, params);
    }

    @Override
    public void delete(Integer id) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        JDBCUtils.executeQuery(QUERY_DELETE, params);
    }

    @Override
    public void addCouponPurchase(int customerId, int couponId) throws JDBCException {
        couponsCustomersDAO.addCoupon(customerId, couponId);
    }
}
