package facade;


import beans.cliens.Company;
import exceptions.ClientExistException;
import exceptions.JDBCException;
import exceptions.LoginException;
import facade.login.ClientType;
import facade.login.LoginManager;

public class AdminTest {
    private static AdminFacade adminFacade = null;

    public static void test() {
        adminFacade = login();
        System.out.println("adminFacade:" + adminFacade);
        if (adminFacade != null) {
            addCompanies();
            updateCompanies();
        }
    }

    private static void addCompanies() {
        try {
            adminFacade.addCompany(new Company("ElAl", "ElAl@gmail.com", "ElAl_password"));
            adminFacade.addCompany(new Company("ZARA", "ZARA@gmail.com", "ZARA_password"));
            adminFacade.addCompany(new Company("GOLDA", "GOLDA@gmail.com", "GOLDA_password"));
            adminFacade.addCompany(new Company("ElAl", "ElAl@gmail.com", "ElAl_password"));
        } catch (Exception e) {
            System.out.println("addCompanies:" + e);
        }
    }

    private static void updateCompanies() {
        try {
            adminFacade.updateCompany(new Company("ElAl", "ElAl@gmail.com", "ElAl_password"));
        } catch (Exception e) {
            System.out.println("updateCompanies:" + e);
        }
    }

    private static AdminFacade login() {
        return (AdminFacade) LoginManager.getInstance().login("admin@admin.com0", "admin", ClientType.Admin);
    }
}
