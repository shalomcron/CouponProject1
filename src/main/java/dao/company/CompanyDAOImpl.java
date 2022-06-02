package dao.company;

import beans.cliens.Company;
import db.JDBCUtils;
import db.ResultsUtils;
import exceptions.JDBCException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyDAOImpl implements CompanyDAO {
    public static CompanyDAOImpl instance = new CompanyDAOImpl();

    private static final String QUERY_INSERT = "INSERT INTO `coupone-bhp-386`.`companies` (`NAME`, `EMAIL`, `PASSWORD`) " +
            "VALUES (?, ?, ?);\n";
    private static final String QUERY_GET_ALL = "SELECT * FROM `coupone-bhp-386`.companies";
    private static final String QUERY_GET_ONE = "SELECT * FROM `coupone-bhp-386`.companies where id=?";

    private static final String QUERY_UPDATE = "UPDATE `coupone-bhp-386`.`companies` SET " +
            "`name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";
    private static final String QUERY_DELETE = "DELETE FROM `coupone-bhp-386`.`companies` WHERE (`id` = ?);";
    private static final String QUERY_IS_EXIST = "select exists (SELECT * FROM `coupone-bhp-386`.companies " +
            "where EMAIL=? AND PASSWORD=?) as RES;";

    private CompanyDAOImpl() {
    }

    public static CompanyDAOImpl getInstance() {
        return instance;
    }

    @Override
    public void add(Company company) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        JDBCUtils.executeQuery(QUERY_INSERT, params);
    }

    @Override
    public List<Company> getAll() throws JDBCException {
        List<Company> results = new ArrayList<>();
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL);
        for (Map<String, Object> object : rows) {
            results.add(ResultsUtils.companyFromRow(object));
        }
        return results;
    }

    @Override
    public Company getSingle(Integer id) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE, params);
        return ResultsUtils.companyFromRow(rows.get(0));
    }

    @Override
    public void update(Integer id, Company company) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        params.put(4, id);
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
        boolean res = false;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        return ResultsUtils.isExistFromRow(JDBCUtils.executeQueryWithResults(QUERY_IS_EXIST, params).get(0));
    }
}
