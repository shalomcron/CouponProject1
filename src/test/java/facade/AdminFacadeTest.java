package facade;

import beans.cliens.Company;
import beans.cliens.Customer;
import exceptions.JDBCException;
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
        try {
            adminFacade.addCompany(company1);
            System.out.printf("@ addCompany %s finished successfully \n", company1.getName());
        } catch (Exception e) {
            System.out.println("addCustomers ex:" + e);
        }
    }

    public static void updateCompany(int id, Company company) {
        try {
            adminFacade.updateCompany(id, company);
            System.out.printf("@ updateCompany %s finished successfully \n", company.getName());
        } catch (Exception e) {
            System.out.println("updateCompany ex:" + e);
        }
    }

    public static void deleteCompany(Company company) {
        try {
            adminFacade.deleteCompany(company.getId());
            System.out.printf("@ deleteCompany %s finished successfully \n", company.getName());
        } catch (JDBCException e) {
            System.out.println("deleteCompany ex:" + e);
        }
    }

    public static void getAllCompanies() {
        System.out.println("@ getAllCompanies");
        try {
            adminFacade.getAllCompanies().forEach(System.out::println);
        } catch (JDBCException e) {
            System.out.println("getAllCompanies ex:" + e);
        }
    }

    public static void getOneCompany(Company company) {
        System.out.println("@ getOneCompany");
        try {
            System.out.println(adminFacade.getOneCompany(company.getId()));
        } catch (JDBCException e) {
            System.out.println("getOneCompany ex:" + e);
        }
    }

    public static void addCustomer(Customer customer) {
        try {
        adminFacade.addCustomer(customer);
        System.out.printf("@ addCustomer %s finished successfully \n", customer.getFirstName());
        } catch (Exception e) {
            System.out.println("addCustomers ex:" + e);
        }
    }
}
