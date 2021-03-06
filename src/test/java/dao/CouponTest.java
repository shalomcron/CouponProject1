package dao;

import beans.coupone.Category;
import beans.coupone.Coupon;
import dao.coupon.CouponDAO;
import dao.coupon.CouponDAOImpl;
import exceptions.JDBCException;

import java.sql.Date;
import java.time.LocalDate;

public class CouponTest {
    private static final CouponDAO couponDAO = CouponDAOImpl.getInstance();

    public static void test() throws JDBCException {
        System.out.println("*************************");
        System.out.println("*** testCrudCoupon ***");
        System.out.println("*************************");
        insertCoupons();
        getAllCoupons();
        getSingleCoupon();
        // updateCoupon();
        // deleteCoupon();
    }

        public static void insertCoupons() throws JDBCException {
        System.out.println("---------- insertCoupons TEST ---------" + Category.Food.getId());
        Coupon coupon1 = new Coupon(1, Category.Food.getId(), "cop title1", "description2",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 1, 1, "image1");
        Coupon coupon2 = new Coupon(2, Category.Food.getId(), "cop title2", "description2",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 2, 2, "image2");
        Coupon coupon3 = new Coupon(3, Category.Food.getId(), "cop title3", "description3",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 3, 3, "image3");
        Coupon coupon4 = new Coupon(4, Category.Electricity.getId(), "cop title4", "description4",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 4, 4, "image4");
        Coupon coupon5 = new Coupon(5, Category.Electricity.getId(), "cop title5", "description5",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 5, 5, "image5");
        couponDAO.add(coupon1);
        couponDAO.add(coupon2);
        couponDAO.add(coupon3);
        couponDAO.add(coupon4);
        couponDAO.add(coupon5);
    }

    public static void getAllCoupons() throws JDBCException {
        System.out.println("---------- getAllCoupons TEST ---------");
        couponDAO.getAll().forEach(System.out::println);
    }

    public static void getSingleCoupon() throws JDBCException {
        getSingleCoupon(1);
    }

    public static void getSingleCoupon(int id) throws JDBCException {
        System.out.println("---------- getSingleCoupon for ID:" + id + " TEST ---------");
        System.out.println(couponDAO.getSingle(id));
    }

    public static void updateCoupon() throws JDBCException {
        int id = 2;
        System.out.println("Before update Coupon ID:" + id);
        Coupon coupon = new Coupon(1, 1, "update title", "update description",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 1, 1, "update image");
        getSingleCoupon(id);
        couponDAO.update(id, coupon);
        System.out.println("After update Coupon ID:" + id);
        getSingleCoupon(id);
    }

    public static void deleteCoupon() throws JDBCException {
        int id = 3;
        System.out.println("Before delete Coupon " + id);
        getAllCoupons();
        couponDAO.delete(id);
        System.out.println("After delete Coupon " + id);
        getAllCoupons();
    }

}
