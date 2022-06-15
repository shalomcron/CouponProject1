package facade;

import beans.cliens.Company;
import beans.coupone.Category;
import beans.coupone.Coupon;
import facade.clients.ClientType;
import facade.clients.CompanyFacade;
import facade.login.LoginManager;
import facade.store.CompanyStore;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class CompanyFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static final Company taraComp = CompanyStore.getTaraComp();
    private static final CompanyFacade companyFacade = (CompanyFacade) loginManager.login(taraComp.getEmail(), taraComp.getPassword(), ClientType.Company);

    public static void companyTest() {
        if (companyFacade != null) {
            System.out.println("@ Company " + taraComp.getName() + " has logged in");
            addCoupons();
            updateCoupon();
            deleteCoupon();
            getAllCoupons();
            getAllCategoryCoupons();
            getAllMaxPriceCoupons();
            getCompanyDetails();
        }
    }

    private static void getCompanyDetails() {
        try {
            System.out.println("@ getCompanyDetails");
            System.out.println(companyFacade.getCompanyDetails());
        } catch (Exception e) {
            System.out.println("getCompanyDetails ex:" + e);
        }
    }

    private static void getAllMaxPriceCoupons() {
        try {
            System.out.println("@ getAllMaxPriceCoupons");
            companyFacade.getAllCoupons(50).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("getAllMaxPriceCoupons ex:" + e);
        }
    }

    private static void getAllCategoryCoupons() {
        try {
            System.out.println("@ getAllCategoryCoupons");
            companyFacade.getAllCoupons(Category.Restaurant).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("getAllCategoryCoupons ex:" + e);
        }
    }

    private static void getAllCoupons() {
        try {
            System.out.println("@ getAllCoupons");
            companyFacade.getAllCoupons().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("getAllCoupons ex:" + e);
        }
    }

    private static void deleteCoupon() {
        try {
            companyFacade.deleteCoupon(2);
            System.out.println("@ deleteCoupon finished successfully");
        } catch (Exception e) {
            System.out.println("deleteCoupon ex:" + e);
        }
    }

    private static void addCoupons() {
        try {
            companyFacade.addCoupon(getCheeseCoupon(0, 0));
            companyFacade.addCoupon(getMiilkCoupon());
            companyFacade.addCoupon(getYogurtCoupon());
            companyFacade.addCoupon(getTripCoupone());
            companyFacade.addCoupon(getRestaurantCoupone());
            System.out.println("@ addCoupon finished successfully");
            System.out.println("@ add coupon same title not allowed");
            companyFacade.addCoupon(getCheeseCoupon(2, 2));
        } catch (Exception e) {
            System.out.println("addCoupon ex:" + e);
        }
    }

    private static void updateCoupon() {
        try {
            Coupon couponToUpdate1 = getCheeseCoupon(1, companyFacade.getCompanyId());
            couponToUpdate1.setImage("UPDATE_IMG");
            companyFacade.updateCoupon(1, couponToUpdate1);
            System.out.println("@ updateCoupon ended successfully");
            System.out.println("@ updateCoupon companyId not allowed");
            Coupon couponToUpdate2 = getCheeseCoupon(1, 3);
            companyFacade.updateCoupon(1, couponToUpdate2);
        } catch (Exception e) {
            System.out.println("updateCoupon ex: " + e);
        }
    }


    private static Coupon getCheeseCoupon(int id, int companyId) {
        int amount = 3;
        if (id > 0) {
            return new Coupon(id, companyId, Category.Food.getId(), "גבינות", "קופון 10% הנחה על גבינות השמנת",
                    Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plus(1, ChronoUnit.MONTHS)), amount, 10, "image");
        }
        return new Coupon("גבינות", Category.Food.getId(), "קופון 10% הנחה על גבינות השמנת",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plus(1, ChronoUnit.MONTHS)), amount, 10, "image");
    }

    private static Coupon getYogurtCoupon() {
        int amount = 2;
        return new Coupon("יוגורט", Category.Food.getId(), "קופון 30% הנחה על יודורט",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), amount, 30, "image");
    }

    private static Coupon getMiilkCoupon() {
        int amount = 1;
        return new Coupon("חלב", Category.Food.getId(), "קופון 40% הנחה על חלב",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), amount, 40, "image");
    }

    private static Coupon getTripCoupone() {
        return new Coupon("טיול", Category.Vacation.getId(), "קופון 50% לטיול באירופה",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 10, 50, "image");
    }

    private static Coupon getRestaurantCoupone() {
        return new Coupon("מסעדה", Category.Restaurant.getId(), "קופון 60% למסעדה",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 10, 60, "image");
    }
}
