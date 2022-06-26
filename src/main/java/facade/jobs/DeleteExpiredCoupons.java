package facade.jobs;

import beans.coupone.Coupon;
import dao.coupon.CouponDAO;
import dao.coupon.CouponDAOImpl;
import dao.couponsCustomers.CouponsCustomersDAO;
import dao.couponsCustomers.CouponsCustomersDAOImpl;

import java.util.List;

public class DeleteExpiredCoupons implements Runnable {
    long MIL_SEC_TO_SLEEP = 24 * 60 * 60 * 1000;

    protected static final CouponDAO couponDAO = CouponDAOImpl.getInstance();
    protected static final CouponsCustomersDAO couponsCustomersDAO = CouponsCustomersDAOImpl.getInstance();

    private boolean quit = false;
    @Override
    public void run() {
        while (!quit) {
            try {
                List<Coupon> coupons = couponDAO.getAllExpiredCoupons();
                System.out.println("DeleteExpiredCoupons coupons:");
                coupons.forEach(System.out::println);
                for (Coupon coupon: coupons) {
                    int couponId = coupon.getId();
                    couponsCustomersDAO.deleteCouponPurchases(couponId);
                    couponDAO.delete(couponId);
                }
                System.out.println("DeleteExpiredCoupons finished successfully");
                Thread.sleep(MIL_SEC_TO_SLEEP);
            } catch (Exception e) {
                System.out.println("DeleteExpiredCoupons ex:" + e);
                stop();
            }
        }
    }

    public void stop() {
        quit = true;
    }
}
