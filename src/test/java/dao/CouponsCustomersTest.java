package dao;

import beans.couponsCustomer.CouponsCustomer;
import dao.coupon.CouponDAO;
import dao.coupon.CouponDAOImpl;
import dao.couponsCustomers.CouponsCustomersDAOImpl;
import exceptions.JDBCException;

public class CouponsCustomersTest {
    private static final CouponsCustomersDAOImpl couponsCustomersDAO = CouponsCustomersDAOImpl.getInstance();
    private static final CouponsCustomer couponsCustomer1 = new CouponsCustomer(1, 1);

    public static void test() {
        System.out.println("*************************");
        System.out.println("*** CouponsCustomersTest ***");
        System.out.println("*************************");
        purchaseCoupons();
//        getAllCoupons();
//        getSingleCoupon();
        // updateCoupon();
        // deleteCoupon();
//        addCouponPurchase();
//        deleteCouponPurchase();
    }

    private static void purchaseCoupons() {
        try {
            couponsCustomersDAO.add(couponsCustomer1);
            System.out.println("@ purchaseCoupons finished successfully");
        } catch (Exception e) {
            System.out.println("purchaseCoupons ex:" + e);
        }
    }
}
