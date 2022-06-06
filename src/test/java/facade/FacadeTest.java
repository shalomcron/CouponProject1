package facade;

import beans.cliens.Company;
import beans.cliens.Customer;
import exceptions.JDBCException;
import facade.clients.AdminFacade;
import facade.clients.ClientType;
import facade.login.LoginManager;

public class FacadeTest {
    private static AdminFacade adminFacade = null;
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static Company elalComp = new Company("EL-AL", "elal@gmail.com", "elal_password");
    private static Company zaralComp = new Company("ZARA", "zara@gmail.com", "zara_password");
    private static Company aldoComp = new Company("ALDO", "aldo@gmail.com", "aldo_password");

    private static Customer yosef = new Customer("Yosef", "YosefFamily", "yosef@gmail.com", "yosef_password");
    private static Customer david = new Customer("David", "DavidFamily", "david@gmail.com", "david_password");
    private static Customer shay = new Customer("Shay", "ShayFamily", "shy@gmail.com", "shy_password");

    public static void adminTest() {
        adminFacade = (AdminFacade) loginManager.login("admin@admin.com", "admin", ClientType.Admin);
        if (adminFacade != null) {
            // Companies
            addCompanies();
            addClientIdsFromDB();
            updateCompany();
            deleteCompany();
            getAllCompanies();
            getOneCompany();

            // Customers
            addCustomers();
            updateCustomer();
            deleteCustomer();
            getAllCustomers();
            getOneCustomer();



        }
    }

    private static void addCustomers() {
        try {
            adminFacade.addCustomer(yosef);
            adminFacade.addCustomer(david);
            adminFacade.addCustomer(shay);
            System.out.println("@ addCustomers finished successfully");
        } catch (JDBCException e) {
            System.out.println("addCustomers ex:" + e);
        }
    }

    private static void updateCustomer() {
    }
    private static void deleteCustomer() {
    }
    private static void getAllCustomers() {
    }
    private static void getOneCustomer() {
    }


    private static void getOneCompany() {
        System.out.println("@ getOneCompany");
        try {
            System.out.println(adminFacade.getOneCompany(elalComp.getId()));
        } catch (JDBCException e) {
            System.out.println("getOneCompany:" + e);
        }
    }

    private static void getAllCompanies() {
        System.out.println("@ getAllCompanies");
        try {
            adminFacade.getAllCompanies().forEach(System.out::println);
        } catch (JDBCException e) {
            System.out.println("getAllCompanies:" + e);
        }
    }

    private static void deleteCompany() {
        try {
            adminFacade.deleteCompany(aldoComp.getId());
            System.out.println("@ deleteCompany " + aldoComp.getName() + " finished successfully");
        } catch (JDBCException e) {
            System.out.println("deleteCompany:" + e);
        }
    }

    private static void addClientIdsFromDB() {
        try {
            elalComp = adminFacade.geSingle(elalComp.getEmail(), elalComp.getPassword());
            zaralComp = adminFacade.geSingle(zaralComp.getEmail(), zaralComp.getPassword());
            aldoComp = adminFacade.geSingle(aldoComp.getEmail(), aldoComp.getPassword());
            System.out.println("@ addClientIdsFromDB finished successfully");
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
