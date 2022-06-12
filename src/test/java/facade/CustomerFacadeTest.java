package facade;

import facade.clients.ClientType;
import facade.clients.CustomerFacade;
import facade.login.LoginManager;

public class CustomerFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static final CustomerFacade companyFacade = (CustomerFacade) loginManager.login("david@gmail.com", "david_password", ClientType.Customer);

    public static void customerTest() {
        System.out.println("companyFacade:" + companyFacade);
    }
}
