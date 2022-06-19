package facade;

import beans.cliens.Company;
import beans.coupone.Coupon;
import facade.clients.ClientType;
import facade.clients.CompanyFacade;
import facade.clients.CustomerFacade;
import facade.login.LoginManager;

public class CompanyFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();

    public static CompanyFacade login(Company company) {
        return (CompanyFacade) loginManager.login(company.getEmail(), company.getPassword(), ClientType.Company);
    }


    public static void addCoupons(CompanyFacade companyFacade, Coupon coupon) {
        System.out.printf("@ addCoupon for company %s(%s), coupon %s \n", companyFacade.getCompanyId(), companyFacade.getCompanyName(), coupon.getTitle());
        try {
            companyFacade.addCoupon(coupon);
            System.out.println("@ addCoupon finished successfully");
        } catch (Exception e) {
            System.out.println("addCoupon ex:" + e);
        }
    }

    public static void updateCoupon(CompanyFacade companyFacade, int id, Coupon coupon) {
        try {
            System.out.printf("@ updateCoupon for company %s(%s), coupon %s(%s) \n",
                    companyFacade.getCompanyId(), companyFacade.getCompanyName(), id ,coupon.getTitle());
            companyFacade.updateCoupon(id, coupon);
            System.out.println("@ updateCoupon finished successfully");
        } catch (Exception e) {
            System.out.println("updateCoupon ex: " + e);
        }
    }

    public static void deleteCoupon(CompanyFacade companyFacade, int couponId) {
        try {
            System.out.printf("@ deleteCoupon %s \n", couponId);
            companyFacade.deleteCoupon(couponId);
            System.out.println("@ deleteCoupon finished successfully");
        } catch (Exception e) {
            System.out.println("deleteCoupon ex:" + e);
        }
    }
}
