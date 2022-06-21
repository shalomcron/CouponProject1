package jobs;

import dao.coupon.CouponDAO;
import dao.coupon.CouponDAOImpl;

public class DeleteExpiredCoupons implements Runnable {
    long MIL_SEC_TO_SLEEP = 24 * 60 * 60 * 1000;

    protected static final CouponDAO couponDAO = CouponDAOImpl.getInstance();
    private boolean quit = false;
    @Override
    public void run() {
        while (!quit) {
            try {
                couponDAO.deleteExpiredCoupons();
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
