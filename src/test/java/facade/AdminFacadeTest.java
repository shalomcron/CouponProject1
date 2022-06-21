package facade;

import beans.cliens.Company;
import beans.cliens.Customer;
import exceptions.JDBCException;
import facade.clients.AdminFacade;
import facade.clients.ClientType;
import login.LoginManager;

public class AdminFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static AdminFacade adminFacade = null;

    public static AdminFacade adminLogin() {
        adminFacade = (AdminFacade) loginManager.login("admin@admin.com", "admin", ClientType.Admin);
        return adminFacade;
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
            // TODO: delete company coupon
            // TODO: delete customers coupon
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

    public static void updateCustomer(int id, Customer customerToUpdate) {
        try {
            adminFacade.updateCustomer(id, customerToUpdate);
            System.out.printf("@ updateCustomer %s finished successfully \n", customerToUpdate.getFirstName());
        } catch (Exception e) {
            System.out.println("updateCustomer ex:" + e);
        }
    }

    public static void deleteCustomer(int id) {
        try {
            adminFacade.deleteCustomer(id);
            System.out.printf("@ deleteCustomer %s finished successfully \n", id);
        } catch (Exception e) {
            System.out.println("deleteCustomer ex:" + e);
        }
    }

    public static void getAllCustomers() {
        System.out.println("@ getAllCustomers");
        try {
            adminFacade.getAllCustomers().forEach(System.out::println);
        } catch (JDBCException e) {
            System.out.println("getAllCustomers ex:" + e);
        }
    }
    public static void getOneCustomer(int id) {
        System.out.printf("@ getOneCustomer %s \n", id);
        try {
            System.out.println(adminFacade.geSingleCompany(id));
        } catch (JDBCException e) {
            System.out.println("getAllCustomers ex:" + e);
        }
    }

}
