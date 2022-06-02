package dao.category;

import beans.Category;
import db.JDBCUtils;
import db.ResultsUtils;
import exceptions.JDBCException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDAOImpl implements CategoryDAO {
    private final static CategoryDAOImpl instance = new CategoryDAOImpl();

    private static final String QUERY_INSERT = "INSERT INTO `coupone-bhp-386`.`categories` " +
            "(`NAME`) VALUES (?)";

    private static final String QUERY_GET_ALL = "SELECT * FROM `coupone-bhp-386`.categories";
    private static final String QUERY_GET_ONE = "SELECT * FROM `coupone-bhp-386`.categories where id=?";

    private static final String QUERY_UPDATE = "UPDATE `coupone-bhp-386`.`categories` SET `NAME` = ? WHERE (`ID` = ?);";
    private static final String QUERY_DELETE = "DELETE FROM `coupone-bhp-386`.`categories` WHERE (`ID` = ?);";

    private CategoryDAOImpl() {}

    public static CategoryDAOImpl getInstance() {
        return instance;
    }

    @Override
    public void add(Category company) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.name());
        JDBCUtils.executeQuery(QUERY_INSERT, params);
    }

    @Override
    public List<Category> getAll() throws JDBCException {
        List<Category> results = new ArrayList<>();
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL);
        for (Map<String, Object> object : rows) {
            results.add(ResultsUtils.categoryFromRow(object));
        }
        return results;
    }

    @Override
    public Category getSingle(Integer id) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        List<Map<String, Object>> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE, params);
        return ResultsUtils.categoryFromRow(rows.get(0));
    }

    @Override
    public void update(Integer id, Category company) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.name());
        params.put(2, id);
        JDBCUtils.executeQuery(QUERY_UPDATE, params);
    }

    @Override
    public void delete(Integer id) throws JDBCException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        JDBCUtils.executeQuery(QUERY_DELETE, params);
    }

}
