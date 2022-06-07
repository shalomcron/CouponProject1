package dao.customer;

import beans.cliens.Customer;
import dao.DAO;
import exceptions.JDBCException;

public interface CustomerDAO extends DAO<Customer, Integer> {
    boolean isExist(String email, String password) throws JDBCException;

   Customer getSingle(String email, String password) throws JDBCException;

}
