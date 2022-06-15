package dao;

import beans.couponsCustomer.CouponsCustomer;
import dao.coupon.CouponDAO;
import dao.coupon.CouponDAOImpl;
import dao.couponsCustomers.CouponsCustomersDAOImpl;
import exceptions.JDBCException;

public class CouponsCustomersTest {
    private static final CouponsCustomersDAOImpl couponsCustomersDAO = CouponsCustomersDAOImpl.getInstance();
    private static final CouponsCustomer couponsCustomer1 = new CouponsCustomer(1, 1);
    private static final CouponsCustomer couponsCustomer2 = new CouponsCustomer(2, 2);

    public static void test() {
        System.out.println("*************************");
        System.out.println("*** CouponsCustomersTest ***");
        System.out.println("*************************");
        purchase();
        getAllPurchase();
//        getSingleCoupon();
        // updateCoupon();
        // deleteCoupon();
//        addCouponPurchase();
//        deleteCouponPurchase();
    }

    private static void getAllPurchase() {
        try {
            couponsCustomersDAO.getAll().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("getAllPurchase ex:" + e);
        }
    }

    private static void purchase() {
        try {
            couponsCustomersDAO.add(couponsCustomer1);
            couponsCustomersDAO.add(couponsCustomer2);
            couponsCustomersDAO.add(couponsCustomer1);
            System.out.println("@ purchaseCoupons finished successfully");
        } catch (Exception e) {
            System.out.println("purchaseCoupons ex:" + e);
        }
    }
}
