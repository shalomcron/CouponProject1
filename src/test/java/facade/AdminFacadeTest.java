package facade;

import beans.cliens.Company;
import facade.clients.AdminFacade;
import facade.clients.ClientType;
import facade.login.LoginManager;

public class AdminFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static AdminFacade adminFacade = null;

    public static void adminLogin() {
        adminFacade = (AdminFacade) loginManager.login("admin@admin.com", "admin", ClientType.Admin);
    }

    public static void addCompany(Company company1) {
        if (adminFacade != null) {
            try {
                adminFacade.addCompany(company1);
                System.out.printf("@ addCompany %s finished successfully \n", company1.getName());
            } catch (Exception e) {
                System.out.println("addCustomers ex:" + e);
            }
        }
    }
}
