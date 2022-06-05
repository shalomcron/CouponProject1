package facade;

import beans.cliens.Company;
import facade.clients.AdminFacade;
import facade.clients.ClientType;
import facade.login.LoginManager;

public class FacadeTest {
    private static AdminFacade adminFacade = null;
    private final static Company elalComp = new Company("ElAl", "elal@gmail.com", "elal_password");
    private final static Company zaralComp = new Company("ZARA", "zara@gmail.com", "zara_password");
    private final static Company aldoComp = new Company("ALDO", "aldo@gmail.com", "aldo_password");

    public static void test() {
        adminFacade = login();
        if (adminFacade != null) {
            addCompanies();
        }
    }

    private static void addCompanies() {
        try {
            adminFacade.addCompany(elalComp);
            adminFacade.addCompany(zaralComp);
            adminFacade.addCompany(aldoComp);
            System.out.println("addCompanies finished successfully");
        } catch (Exception e) {
            System.out.println("addCompanies:" + e);
        }
    }

    private static AdminFacade login() {
        return (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.Admin);
    }
}
