package facade;

import beans.cliens.Company;
import beans.coupone.Category;
import beans.coupone.Coupon;
import exceptions.JDBCException;
import facade.clients.ClientType;
import facade.clients.CompanyFacade;
import facade.login.LoginManager;

import java.sql.Date;
import java.time.LocalDate;

public class CompanyFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static final Company taraComp = new Company("TARA", "tara@gmail.com", "tara_password");
    private static final CompanyFacade companyFacade = (CompanyFacade) loginManager.login(taraComp.getEmail(), taraComp.getPassword(), ClientType.Company);

    // Coupons
    private static Coupon cheeseCoupon =
            new Coupon("גבינות", Category.Food.getId(), "קופון 20% הנחה על גבינות השמנת",
                    Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 0, 10, "image");
    private static Coupon miilkCoupon =
            new Coupon("חלב", Category.Food.getId(), "קופון 10% הנחה על חלב",
                    Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 0, 10, "image");
    private static Coupon yogurtCoupon =
            new Coupon("יודורט", Category.Food.getId(), "קופון 5% הנחה על יודורט",
                    Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 0, 10, "image");

    public static void companyTest() {
        if (companyFacade != null) {
            System.out.println("@ Company " + taraComp.getName() + " has logged in");
            addCoupon();
        }
    }

    private static void addCoupon() {
        try {
            companyFacade.addCoupon(cheeseCoupon);
            companyFacade.addCoupon(miilkCoupon);
            companyFacade.addCoupon(yogurtCoupon);
            System.out.println("@ addCoupon finished successfully");
        } catch (JDBCException e) {
            System.out.println("addCoupon ex:" + e);
        }
    }
}
