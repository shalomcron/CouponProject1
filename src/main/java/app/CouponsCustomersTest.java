package app;

import beans.couponsCustomer.CouponsCustomer;
import dao.couponsCustomers.CouponsCustomersDAOImpl;

public class CouponsCustomersTest {
    private static final CouponsCustomersDAOImpl couponsCustomersDAO = CouponsCustomersDAOImpl.getInstance();
    private static final CouponsCustomer couponsCustomer1 = new CouponsCustomer(1, 1);
    private static final CouponsCustomer couponsCustomer2 = new CouponsCustomer(2, 2);

    public static void test() {
        System.out.println("*************************");
        System.out.println("*** CouponsCustomersTest ***");
        System.out.println("*************************");
        purchaseCoupon();
        getAllPurchases();
        getSinglePurchase();
        deleteCouponCustomerPurchase();
    }

    private static void deleteCouponCustomerPurchase() {
        try {
            couponsCustomersDAO.deleteCouponPurchases(1, 1);
            System.out.println("@ deletePurchase finished successfully");
        } catch (Exception e) {
            System.out.println("deletePurchase ex:" + e);
        }
    }

    private static void getSinglePurchase() {
        System.out.println("@ getSinglePurchase");
        try {
            System.out.println(couponsCustomersDAO.getSinglePurchase(1, 1));
        } catch (Exception e) {
            System.out.println("getAllPurchase ex:" + e);
        }
    }

    private static void getAllPurchases() {
        System.out.println("@ getAllPurchase");
        try {
            couponsCustomersDAO.getAllPurchases().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("getAllPurchase ex:" + e);
        }
    }

    private static void purchaseCoupon() {
        try {
            couponsCustomersDAO.purchaseCoupon(couponsCustomer1);
            couponsCustomersDAO.purchaseCoupon(couponsCustomer2);
            couponsCustomersDAO.purchaseCoupon(couponsCustomer1);
            System.out.println("@ purchaseCoupons finished successfully");
        } catch (Exception e) {
            System.out.println("purchaseCoupons ex:" + e);
        }
    }
}
