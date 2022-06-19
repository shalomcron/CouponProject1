package facade;

import facade.clients.ClientType;
import facade.clients.CustomerFacade;
import facade.login.LoginManager;

public class CustomerFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static final CustomerFacade customerFacade = (CustomerFacade) loginManager.login("david@gmail.com", "david_password", ClientType.Customer);

    public static void customerTest() {
        if (customerFacade != null) {
            purchaseCoupons();
            showPurchasedCoupons();
        }
    }

    private static void showPurchasedCoupons() {

    }

    private static void purchaseCoupons() {
        purchaseCoupon(1); // גבינות
        purchaseCoupon(2); // חלב
        purchaseCoupon(2); // חלב
        purchaseCoupon(3); // יוגורט
        purchaseCoupon(4); // טיול
    }
    private static void purchaseCoupon(int couponIdToPurchase) {
        try {
            customerFacade.purchaseCoupon(couponIdToPurchase);
            System.out.println("@ purchaseCoupon " + couponIdToPurchase + " finished successfully");
        } catch (Exception e) {
            System.out.println("purchaseCoupon " + couponIdToPurchase + " ex:" + e);
        }
    }
}
