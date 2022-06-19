package facade;

import beans.cliens.Customer;
import beans.coupone.Coupon;
import facade.clients.ClientType;
import facade.clients.CustomerFacade;
import facade.login.LoginManager;

public class CustomerFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();

    public static CustomerFacade login(Customer company) {
        return (CustomerFacade) loginManager.login(company.getEmail(), company.getPassword(), ClientType.Customer);
    }

    public static void purchaseCoupon(CustomerFacade customerFacade, Coupon coupon) {
        try {
            System.out.printf("@ purchaseCoupon %s for customer %s(%s) \n", coupon.getTitle(), customerFacade.getCustomerId(), customerFacade.getCustomerName());
            customerFacade.purchaseCoupon(coupon.getId());
            System.out.println("@ purchaseCoupon finished successfully");
        } catch (Exception e) {
            System.out.println("purchaseCoupon ex:" + e);
        }
    }

    public static void getPurchasedCoupons(CustomerFacade customerFacade) {
        System.out.printf("@ showPurchasedCouponsfor customer %s(%s) \n", customerFacade.getCustomerId(), customerFacade.getCustomerName());
        try {
            customerFacade.getPurchasedCoupons(customerFacade.getCustomerId()).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("getPurchasedCoupons ex:" + e);
        }
    }
}
