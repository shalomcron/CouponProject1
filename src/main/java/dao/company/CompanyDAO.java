package dao.company;

import beans.Company;
import dao.DAO;
import exceptions.JDBCException;

public interface CompanyDAO extends DAO<Company, Integer> {
    public boolean isExist(String email, String password) throws JDBCException;
}
