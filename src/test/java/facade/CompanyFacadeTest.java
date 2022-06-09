package facade;

import beans.cliens.Company;
import beans.coupone.Category;
import beans.coupone.Coupon;
import exceptions.JDBCException;
import facade.clients.ClientType;
import facade.clients.CompanyFacade;
import facade.login.LoginManager;
import facade.store.CompanyStore;
import facade.store.CouponsStore;

import java.sql.Date;
import java.time.LocalDate;

public class CompanyFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static final Company taraComp = CompanyStore.getTaraComp();
    private static final CompanyFacade companyFacade = (CompanyFacade) loginManager.login(taraComp.getEmail(), taraComp.getPassword(), ClientType.Company);

    // Coupons

    public static void companyTest() {
        if (companyFacade != null) {
            System.out.println("@ Company " + taraComp.getName() + " has logged in");
            addCoupons();
        }
    }

    private static void addCoupons() {
        try {
            companyFacade.addCoupon(CouponsStore.getCheeseCoupon());
            companyFacade.addCoupon(CouponsStore.getMiilkCoupon());
            companyFacade.addCoupon(CouponsStore.getYogurtCoupon());
            System.out.println("@ addCoupon finished successfully");
            // add coupon same title not allowed
            companyFacade.addCoupon(CouponsStore.getCheeseCouponSameTitle());
        } catch (JDBCException e) {
            System.out.println("addCoupon ex:" + e);
        }
    }
}
