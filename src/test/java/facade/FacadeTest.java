package facade;

import beans.cliens.Company;
import exceptions.ClientNotExistException;
import exceptions.JDBCException;
import facade.clients.AdminFacade;
import facade.clients.ClientType;
import facade.clients.CompanyFacade;
import facade.login.LoginManager;

public class FacadeTest {
    private static AdminFacade adminFacade = null;
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static Company elalComp = new Company("EL-AL", "elal@gmail.com", "elal_password");
    private static Company zaralComp = new Company("ZARA", "zara@gmail.com", "zara_password");
    private static Company aldoComp = new Company("ALDO", "aldo@gmail.com", "aldo_password");

    public static void test() {
        adminFacade = (AdminFacade) loginManager.login("admin@admin.com", "admin", ClientType.Admin);
        if (adminFacade != null) {
            addCompanies();
            addClientIdsFromDB();
            updateCompany();
        }
    }

    private static void addClientIdsFromDB() {
        try {
            elalComp = adminFacade.geSingle(elalComp.getEmail(), elalComp.getPassword());
            zaralComp = adminFacade.geSingle(zaralComp.getEmail(), zaralComp.getPassword());
            aldoComp = adminFacade.geSingle(aldoComp.getEmail(), aldoComp.getPassword());
        } catch (JDBCException e) {
            System.out.println("addClientIds:" + e);
        }
    }

    private static void updateCompany() {
        try {
            elalComp.setEmail("elal_changed@gmail.com");
            elalComp.setPassword("elal_changed_password");
            adminFacade.updateCompany(elalComp);
            System.out.println("@ updateCompany finished successfully");
        } catch (Exception e) {
            System.out.println("updateCompany Exception:" + e);
        }
    }

    private static void addCompanies() {
        try {
            adminFacade.addCompany(elalComp);
            adminFacade.addCompany(zaralComp);
            adminFacade.addCompany(aldoComp);
            System.out.println("@ addCompanies finished successfully");
        } catch (Exception e) {
            System.out.println("addCompanies:" + e);
        }
    }

}
