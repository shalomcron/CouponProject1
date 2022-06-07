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
    private static Company elalComp = new Company("EL-AL", "elal@gmail.com", "elal_password");
    private static Company zaralComp = new Company("ZARA", "zara@gmail.com", "zara_password");
    private static Company aldoComp = new Company("ALDO", "aldo@gmail.com", "aldo_password");
    private static Company taraComp = new Company("TARA", "tara@gmail.com", "tara_password");

    private static Customer yosef = new Customer("Yosef", "YosefFamily", "yosef@gmail.com", "yosef_password");
    private static Customer david = new Customer("David", "DavidFamily", "david@gmail.com", "david_password");
    private static Customer shay = new Customer("Shay", "ShayFamily", "shy@gmail.com", "shy_password");

    public static void adminTest() {
        adminFacade = (AdminFacade) loginManager.login("admin@admin.com", "admin", ClientType.Admin);
        if (adminFacade != null) {
            // Companies
            addCompanies();
            getClientsFromDB();
            updateCompany();
            deleteCompany();
            getAllCompanies();
            getOneCompany();

            // Customers
            addCustomers();
            getCustomersFromDB();
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
        yosef.setEmail("changed_yosef@gmail.com");
        yosef.setPassword("changed_yosef_password");
        try {
            adminFacade.updateCustomer(yosef);
            System.out.println("@ updateCustomer finished successfully");
        } catch (JDBCException e) {
            System.out.println("updateCustomer ex:" + e);
        }
    }
    private static void deleteCustomer() {
        try {
            adminFacade.deleteCustomer(shay);
            System.out.println("@ deleteCustomer finished successfully");
        } catch (JDBCException e) {
            System.out.println("deleteCustomer ex:" + e);
        }
    }
    private static void getAllCustomers() {
        System.out.println("@ getAllCustomers");
        try {
            adminFacade.getAllCustomers().forEach(System.out::println);
        } catch (JDBCException e) {
            System.out.println("getAllCustomers ex:" + e);
        }
    }
    private static void getOneCustomer() {
        System.out.println("@ getAllCustomers");
        try {
            System.out.println(adminFacade.geSingleCompany(david.getId()));
        } catch (JDBCException e) {
            System.out.println("getAllCustomers ex:" + e);
        }
    }


    private static void getOneCompany() {
        System.out.println("@ getOneCompany");
        try {
            System.out.println(adminFacade.getOneCompany(elalComp.getId()));
        } catch (JDBCException e) {
            System.out.println("getOneCompany ex:" + e);
        }
    }

    private static void getAllCompanies() {
        System.out.println("@ getAllCompanies");
        try {
            adminFacade.getAllCompanies().forEach(System.out::println);
        } catch (JDBCException e) {
            System.out.println("getAllCompanies ex:" + e);
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

    private static void getClientsFromDB() {
        try {
            elalComp = adminFacade.geSingleCompany(elalComp.getEmail(), elalComp.getPassword());
            zaralComp = adminFacade.geSingleCompany(zaralComp.getEmail(), zaralComp.getPassword());
            aldoComp = adminFacade.geSingleCompany(aldoComp.getEmail(), aldoComp.getPassword());
            taraComp = adminFacade.geSingleCompany(taraComp.getEmail(), taraComp.getPassword());
            System.out.println("@ getClientsFromDB finished successfully");
        } catch (JDBCException e) {
            System.out.println("addClientIds:" + e);
        }
    }

    private static void getCustomersFromDB() {
        try {
            yosef = adminFacade.geSingleCustomer(yosef.getEmail(), yosef.getPassword());
            david = adminFacade.geSingleCustomer(david.getEmail(), david.getPassword());
            shay = adminFacade.geSingleCustomer(shay.getEmail(), shay.getPassword());
            System.out.println("@ getCustomersFromDB finished successfully");
        } catch (JDBCException e) {
            System.out.println("getCustomersFromDB ex:" + e);
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
            adminFacade.addCompany(taraComp);
            System.out.println("@ addCompanies finished successfully");
        } catch (Exception e) {
            System.out.println("addCompanies:" + e);
        }
    }

}
