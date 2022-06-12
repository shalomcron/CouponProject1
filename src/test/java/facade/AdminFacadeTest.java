package facade;

import beans.cliens.Company;
import beans.cliens.Customer;
import exceptions.JDBCException;
import facade.clients.AdminFacade;
import facade.clients.ClientType;
import facade.login.LoginManager;
import facade.store.CompanyStore;
import facade.store.CustomerStore;

public class AdminFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static AdminFacade adminFacade = null;

    private static Company testComp = new Company("testComp", "test@gmail.com", "test_password");
    private static Customer testCustomer = new Customer("testCustomer", "testFamily", "test@gmail.com", "test_password");


    public static void adminTest() {
        adminFacade = (AdminFacade) loginManager.login("admin@admin.com", "admin", ClientType.Admin);
        if (adminFacade != null) {
            // Companies
            addCompanies();
            // add id company to company objects
            getClientsFromDB();
            updateCompany();
//            deleteCompany();
//            getAllCompanies();
//            getOneCompany();
//
//            // Customers
//            addCustomers();
//            // add id company to customers objects
//            getCustomersFromDB();
//            updateCustomer();
//            deleteCustomer();
//            getAllCustomers();
//            getOneCustomer();
        }
    }

    private static void addCustomers() {
        try {
            adminFacade.addCustomer(CustomerStore.getYosef());
            adminFacade.addCustomer(CustomerStore.getDavid());
            adminFacade.addCustomer(testCustomer);
            System.out.println("@ addCustomers finished successfully");
        } catch (JDBCException e) {
            System.out.println("addCustomers ex:" + e);
        }
    }

    private static void updateCustomer() {
        Customer yosef = CustomerStore.getYosef();
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
            adminFacade.deleteCustomer(testCustomer);
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
            System.out.println(adminFacade.geSingleCompany(CustomerStore.getDavid().getId()));
        } catch (JDBCException e) {
            System.out.println("getAllCustomers ex:" + e);
        }
    }


    private static void getOneCompany() {
        System.out.println("@ getOneCompany");
        try {
            System.out.println(adminFacade.getOneCompany(CompanyStore.getElalComp().getId()));
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
            adminFacade.deleteCompany(testComp.getId());
            System.out.println("@ deleteCompany " + testComp.getName() + " finished successfully");
        } catch (JDBCException e) {
            System.out.println("deleteCompany:" + e);
        }
    }

    private static void getClientsFromDB() {
        try {
            Company elalComp = CompanyStore.getElalComp();
            Company zaralComp = CompanyStore.getZaralComp();
            Company taraComp = CompanyStore.getTaraComp();
            testComp = adminFacade.geSingleCompany(testComp.getEmail(), testComp.getPassword());
            CompanyStore.setElalComp(adminFacade.geSingleCompany(elalComp.getEmail(), elalComp.getPassword()));
            CompanyStore.setZaralComp(adminFacade.geSingleCompany(zaralComp.getEmail(), zaralComp.getPassword()));
            CompanyStore.setTaraComp(adminFacade.geSingleCompany(taraComp.getEmail(), taraComp.getPassword()));
            System.out.println("@ getClientsFromDB finished successfully");
        } catch (JDBCException e) {
            System.out.println("addClientIds:" + e);
        }
    }

    private static void getCustomersFromDB() {
        try {
            Customer yosef = CustomerStore.getYosef();
            Customer david = CustomerStore.getDavid();
            CustomerStore.setYosef(adminFacade.geSingleCustomer(yosef.getEmail(), yosef.getPassword()));
            CustomerStore.setDavid(adminFacade.geSingleCustomer(david.getEmail(), david.getPassword()));
            testCustomer = adminFacade.geSingleCustomer(testCustomer.getEmail(), testCustomer.getPassword());
            System.out.println("@ getCustomersFromDB finished successfully");
        } catch (JDBCException e) {
            System.out.println("getCustomersFromDB ex:" + e);
        }
    }

    private static void updateCompany() {
        try {
            Company elalComp = CompanyStore.getElalComp();
            elalComp.setEmail("elal_changed@gmail.com");
            elalComp.setPassword("elal_changed_password");
            adminFacade.updateCompany(elalComp.getId(), elalComp);
            System.out.println("@ updateCompany finished successfully");
            // trying to update id
            adminFacade.updateCompany(11, elalComp);
        } catch (Exception e) {
            System.out.println("updateCompany Exception:" + e);
        }
    }

    private static void addCompanies() {
        try {
            Company elalComp = CompanyStore.getElalComp();
            Company zaralComp = CompanyStore.getZaralComp();
            Company taraComp = CompanyStore.getTaraComp();
            adminFacade.addCompany(testComp);
            adminFacade.addCompany(elalComp);
            adminFacade.addCompany(zaralComp);
            adminFacade.addCompany(taraComp);
            System.out.println("@ addCompanies finished successfully");
            // add same company name
            adminFacade.addCompany(testComp);
        } catch (Exception e) {
            System.out.println("addCompanies:" + e);
        }
    }

}
