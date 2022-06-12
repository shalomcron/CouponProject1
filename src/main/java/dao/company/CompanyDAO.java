package dao.company;

import beans.cliens.Company;
import dao.DAO;
import exceptions.JDBCException;

public interface CompanyDAO extends DAO<Company, Integer> {
    boolean isExist(String email, String password) throws JDBCException;

    Company getSingle(String email, String password) throws JDBCException;

    boolean isExistByName(String name) throws JDBCException;

    boolean isExistByEmail(String name) throws JDBCException;
}
