package facade;

import exceptions.JDBCException;
import facade.clients.ClientType;
import facade.clients.CustomerFacade;
import facade.login.LoginManager;

public class CustomerFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static final CustomerFacade customerFacade = (CustomerFacade) loginManager.login("david@gmail.com", "david_password", ClientType.Customer);

    public static void customerTest() {
        if (customerFacade != null) {
            purchaseCoupon(1); // גבינות
            purchaseCoupon(2); // יוגורט
        }
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
