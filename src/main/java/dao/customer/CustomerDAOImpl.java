package dao.customer;

import beans.cliens.Customer;
import db.JDBCUtils;
import db.ResultsUtils;
import exceptions.JDBCException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAOImpl implements CustomerDAO {
    private final static CustomerDAOImpl instance = new CustomerDAOImpl();

    private static final String QUERY_INSERT = "INSERT INTO `coupone-bhp-386`.`customers` " +
            "(`FIRST_NAME`, `LAST_NAME`, `EMAIL`, `PASSWORD`) VALUES (?, ?, ?, ?)";

    private static final String QUERY_GET_ALL = "SELECT * FROM `coupone-bhp-386`.customers";
    private static final String QUERY_GET_ONE = "SELECT * FROM `coupone-bhp-386`.customers where id=?";
    private static final String QUERY_GET_ONE_BY_MAIL_PASS =
            "SELECT * FROM `coupone-bhp-386`.customers where EMAIL=? AND PASSWORD=?";

    private static final String QUERY_UPDATE = "UPDATE `coupone-bhp-386`.`customers` SET " +
            "`FIRST_NAME` = ?, `LAST_NAME` = ?, `EMAIL` = ?, `PASSWORD` = ? WHERE (`ID` = ?);";
    private static final String QUERY_DELETE = "DELETE FROM `coupone-bhp-386`.`customers` WHERE (`id` = ?);";

    private static final String QUERY_IS_EXIST = "select exists (SELECT * FROM `coupone-bhp-386`.customers " +
            "where EMAIL=? AND PASSWORD=?) as RES;";
    private static final String QUERY_IS_EXIST_BY_EMAIL = "select exists (SELECT * FROM `coupone-bhp-386`.customers " +
            "where EMAIL=?) as RES;";

    private CustomerDAOImpl() {
    }

    public static CustomerDAOImpl getInstance() {
        return instance;
    }

    @Override
    public void add(Customer customer) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());
        JDBCUtils.executeQuery(QUERY_INSERT, params);
    }

    @Override
    public List<Customer> getAll() throws JDBCException {
        List<Customer> results = new ArrayList<>();
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL);
        for (Map<String, Object> object : rows) {
            results.add(ResultsUtils.customerFromRow(object));
        }
        return results;
    }

    @Override
    public Customer getSingle(Integer id) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE, params);
        return ResultsUtils.customerFromRow(rows.get(0));
    }


    @Override
    public void update(Integer id, Customer customer) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());
        params.put(5, id);
        JDBCUtils.executeQuery(QUERY_UPDATE, params);
    }

    @Override
    public void delete(Integer id) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        JDBCUtils.executeQuery(QUERY_DELETE, params);
    }

    @Override
    public boolean isExist(String email, String password) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        return ResultsUtils.isExistFromRow(JDBCUtils.executeQueryWithResults(QUERY_IS_EXIST, params).get(0));
    }

    @Override
    public boolean isExistByEmail(String email) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        return ResultsUtils.isExistFromRow(JDBCUtils.executeQueryWithResults(QUERY_IS_EXIST_BY_EMAIL, params).get(0));
    }

    @Override
    public Customer getSingle(String email, String password) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE_BY_MAIL_PASS, params);
        return rows.size() > 0 ? ResultsUtils.customerFromRow(rows.get(0)) : null;
    }

}
