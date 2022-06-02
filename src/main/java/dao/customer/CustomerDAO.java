package dao.customer;

import beans.Customer;
import dao.DAO;
import exceptions.JDBCException;

public interface CustomerDAO extends DAO<Customer, Integer> {
    boolean isExist(String email, String password) throws JDBCException;
}
