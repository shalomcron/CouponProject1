package app;

import beans.cliens.Company;
import beans.coupone.Category;
import beans.coupone.Coupon;
import facade.clients.ClientType;
import facade.clients.CompanyFacade;
import login.LoginManager;

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

    public static void getAllCoupons(CompanyFacade companyFacade) {
        try {
            System.out.printf("@ getAllCoupons for company %s(%s) %s \n", companyFacade.getCompanyId(), companyFacade.getCompanyId(), companyFacade.getCompanyName());
            companyFacade.getAllCoupons().forEach(System.out::println);
            System.out.println("@ deleteCoupon finished successfully");
        } catch (Exception e) {
            System.out.println("deleteCoupon ex:" + e);
        }
    }

    public static void getAllCategoryCoupons(CompanyFacade companyFacade, Category category) {
        try {
            System.out.println("@ getAllCategoryCoupons");
            companyFacade.getAllCoupons(Category.Restaurant).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("getAllCategoryCoupons ex:" + e);
        }
    }

    public static void getAllMaxPriceCoupons(CompanyFacade companyFacade, double maxPrice) {
        try {
            System.out.println("@ getAllMaxPriceCoupons");
            companyFacade.getAllCoupons(maxPrice).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("getAllMaxPriceCoupons ex:" + e);
        }
    }


    public static void getCompanyDetails(CompanyFacade companyFacade) {
        try {
            System.out.println("@ getCompanyDetails");
            System.out.println(companyFacade.getCompanyDetails());
        } catch (Exception e) {
            System.out.println("getCompanyDetails ex:" + e);
        }
    }
}
