package dao;

import dao.couponsCustomers.CouponsCustomersDAOImpl;
import exceptions.JDBCException;

public class CouponsCustomersTest {
    private static CouponsCustomersDAOImpl couponsCustomersDAO = CouponsCustomersDAOImpl.getInstance();
    public static void test() throws JDBCException {
        System.out.println("*************************");
        System.out.println("*** testCrudCouponsVsCustomers ***");
        System.out.println("*************************");
        insertCouponsCustomers();
        getAllCouponsCustomers();
    }

    private static void insertCouponsCustomers() throws JDBCException {
        System.out.println("---------- insert CouponsVsCustomers TEST ---------");
        couponsCustomersDAO.addCoupon(4, 4);
        couponsCustomersDAO.addCoupon(5, 5);
    }

    private static void getAllCouponsCustomers() {
        System.out.println("---------- getAllCouponsCustomers TEST ---------");
        // couponsCustomersDAO.(1, 1);
    }
}
