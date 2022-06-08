package facade.store;

import beans.coupone.Category;
import beans.coupone.Coupon;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CouponsStore {
    private static final Coupon cheeseCoupon =
            new Coupon("גבינות", Category.Food.getId(), "קופון 20% הנחה על גבינות השמנת",
                    Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plus(1, ChronoUnit.MONTHS)), 0, 10, "image");
    private static final Coupon miilkCoupon =
            new Coupon("חלב", Category.Food.getId(), "קופון 10% הנחה על חלב",
                    Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 0, 10, "image");
    private static final Coupon yogurtCoupon =
            new Coupon("יודורט", Category.Food.getId(), "קופון 5% הנחה על יודורט",
                    Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 0, 10, "image");

    public static Coupon getCheeseCoupon() {
        // clone
        return new Coupon(cheeseCoupon);
    }

    public static Coupon getMiilkCoupon() {
        // clone
        return new Coupon(miilkCoupon);
    }

    public static Coupon getYogurtCoupon() {
        // clone
        return new Coupon(yogurtCoupon);
    }
}
