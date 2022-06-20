package dao.coupon;

import beans.coupone.Coupon;
import db.JDBCUtils;
import db.ResultsUtils;
import exceptions.JDBCException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponDAOImpl implements CouponDAO {
    private static final CouponDAOImpl instance = new CouponDAOImpl();

    private static final String QUERY_INSERT = "INSERT INTO `coupone-bhp-386`.`coupons` " +
            "(`ID_COMPANY`, `ID_CATEGORY`, `TITLE`, `DESCRIPTION`, `DATE_START`, `DATE_END`, `AMOUNT`, `PRICE`, `IMAGE`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String QUERY_GET_ALL = "SELECT * FROM `coupone-bhp-386`.coupons";

    private static final String QUERY_GET_COMPANY_COUPONS = "SELECT * FROM `coupone-bhp-386`.coupons " +
            "WHERE (ID_COMPANY = ?) ORDER BY ID";

    private static final String QUERY_GET_COMPANY_COUPONS_CATEGORY = "SELECT * FROM `coupone-bhp-386`.coupons " +
            "WHERE (ID_COMPANY = ? AND ID_CATEGORY = ?) ORDER BY ID";

    private static final String QUERY_GET_COMPANY_COUPONS_MAX_PRICE = "SELECT * FROM `coupone-bhp-386`.coupons " +
            "WHERE (ID_COMPANY = ? AND PRICE <= ?) ORDER BY ID";
    private static final String QUERY_GET_ONE = "SELECT * FROM `coupone-bhp-386`.coupons WHERE id=?";

    private static final String QUERY_GET_COUPON_COMPANY = "SELECT * FROM `coupone-bhp-386`.coupons WHERE ID=? AND ID_COMPANY = ?";

    private static final String QUERY_UPDATE = "UPDATE `coupone-bhp-386`.`coupons` " +
            "SET `ID_COMPANY` = ?, `ID_CATEGORY` = ?, `TITLE` = ?, `DESCRIPTION` = ?, " +
            "`DATE_START` = ?, `DATE_END` = ?, `AMOUNT` = ?, `PRICE` = ?, `IMAGE` = ? WHERE (`ID` = ?);";

    private static final String QUERY_REDUCE_AMOUT_COUPONE_OMPANY = "UPDATE `coupone-bhp-386`.`coupons` " +
            "SET `AMOUNT` = ? WHERE ID=? AND ID_COMPANY = ?";

    private static final String QUERY_DELETE = "DELETE FROM `coupone-bhp-386`.`coupons` WHERE (`ID` = ?)";

    private static final String QUERY_DELETE_EXPIRED_COUPONS = "DELETE FROM `coupone-bhp-386`.`coupons`" +
            " WHERE ID>0 AND DATE_END < CURDATE()"; // to avoid error: 1175. You are using safe update mode and you tried to update a table without a WHERE that uses a KEY column

    private static final String QUERY_IS_EXIST_BY_TITLE = "select exists (SELECT * FROM `coupone-bhp-386`.coupons " +
            "WHERE (`ID_COMPANY` = ? and `TITLE` = ?) ) as RES;";


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
    public List<Coupon> getAllCompanyCoupons(int companyId) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);
        List<Coupon> results = new ArrayList<>();
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_COMPANY_COUPONS, params);
        for (Map<String, Object> object : rows) {
            results.add(ResultsUtils.couponFromRow(object));
        }
        return results;
    }

    @Override
    public List<Coupon> getAllCompanyCoupons(int companyId, int categoryId) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);
        params.put(2, categoryId);
        List<Coupon> results = new ArrayList<>();
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_COMPANY_COUPONS_CATEGORY, params);
        for (Map<String, Object> object : rows) {
            results.add(ResultsUtils.couponFromRow(object));
        }
        return results;
    }

    @Override
    public List<Coupon> getAllCompanyCoupons(int companyId, double maxPrice) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);
        params.put(2, maxPrice);
        List<Coupon> results = new ArrayList<>();
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_COMPANY_COUPONS_MAX_PRICE, params);
        for (Map<String, Object> object : rows) {
            results.add(ResultsUtils.couponFromRow(object));
        }
        return results;
    }

    @Override
    public void deleteExpiredCoupons() throws JDBCException {
        JDBCUtils.executeQuery(QUERY_DELETE_EXPIRED_COUPONS);
    }

    @Override
    public Coupon getSingle(Integer id) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE, params);
        return rows.size() > 0 ? ResultsUtils.couponFromRow(rows.get(0)): null;
    }

    @Override
    public Coupon getCouponCompany(int couponId, int companyId) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponId);
        params.put(2, companyId);
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_COUPON_COMPANY, params);
        return rows.size() > 0 ? ResultsUtils.couponFromRow(rows.get(0)): null;
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
    public void reduceAmountCouponCompany(int couponId, int companyId, Coupon coupon) throws JDBCException {
        int amount = coupon.getAmount() -1;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, amount);
        params.put(2, couponId);
        params.put(3, companyId);
        JDBCUtils.executeQuery(QUERY_REDUCE_AMOUT_COUPONE_OMPANY, params);
    }

    @Override
    public void delete(Integer id) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        JDBCUtils.executeQuery(QUERY_DELETE, params);
    }

    @Override
    public boolean isExistByTitle(int companyId, String title) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);
        params.put(2, title);
        return ResultsUtils.isExistFromRow(JDBCUtils.executeQueryWithResults(QUERY_IS_EXIST_BY_TITLE, params).get(0));
    }

}
