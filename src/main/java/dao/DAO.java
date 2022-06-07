package dao;

import beans.cliens.Company;
import exceptions.JDBCException;

import java.util.List;

/**
 * Created by kobis on 15 May, 2022
 */
public interface DAO<T, ID> {
    void add(T t) throws JDBCException;
    List<T> getAll() throws JDBCException;
    T getSingle(ID id) throws JDBCException;
    void update(ID id, T t) throws JDBCException;
    void delete(ID id) throws JDBCException;
}
