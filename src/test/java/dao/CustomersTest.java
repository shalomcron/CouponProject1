package dao;

import beans.Customer;
import dao.customer.CustomerDAO;
import dao.customer.CustomerDAOImpl;
import exceptions.JDBCException;

public class CustomersTest {
    private static final CustomerDAO customerDAO = CustomerDAOImpl.getInstance();

    public static void test() throws JDBCException {
        System.out.println("*************************");
        System.out.println("*** testCrudCustomers ***");
        System.out.println("*************************");
        insertCustomers();
        getAllCustomers();
        getSingleCustomer();
        // updateCustomer();
        // deleteCustomer();
    }

    public static void insertCustomers() throws JDBCException {
        System.out.println("---------- insert customers TEST ---------");
        Customer customer1 = new Customer("firstName1", "lastName1", "email1", "password1");
        Customer customer2 = new Customer("firstName2", "lastName2", "email2", "password2");
        Customer customer3 = new Customer("firstName3", "lastName3", "email3", "password3");
        Customer customer4 = new Customer("firstName4", "lastName4", "email4", "password4");
        Customer customer5 = new Customer("firstName5", "lastName5", "email5", "password5");
        customerDAO.add(customer1);
        customerDAO.add(customer2);
        customerDAO.add(customer3);
        customerDAO.add(customer4);
        customerDAO.add(customer5);
    }

    public static void getAllCustomers() throws JDBCException {
        System.out.println("---------- getAllCustomers TEST ---------");
        customerDAO.getAll().forEach(System.out::println);
    }

    public static void getSingleCustomer() throws JDBCException {
        getSingleCustomer(1);
    }

    public static void getSingleCustomer(int id) throws JDBCException {
        System.out.println("---------- getSingleCustomer for ID:" + id + " TEST ---------");
        System.out.println(customerDAO.getSingle(id));
    }

    public static void updateCustomer() throws JDBCException {
        int id = 2;
        Customer company = new Customer("update firstName", "update lastName", "update email", "update password");
        customerDAO.update(id, company);
        System.out.println("After company update");
        getSingleCustomer(id);
    }

    public static void deleteCustomer() throws JDBCException {
        int id = 3;
        System.out.println("Before delete company");
        getAllCustomers();
        customerDAO.delete(id);
        System.out.println("After delete company");
        getAllCustomers();
    }
}
