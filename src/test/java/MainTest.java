import beans.cliens.Company;
import beans.cliens.Customer;
import db.DatabaseManager;
import exceptions.CompanyException;
import exceptions.JDBCException;
import facade.AdminFacadeTest;
import facade.clients.AdminFacade;
import facade.clients.ClientType;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("----- Main Tests START -----");
        System.out.println("---------- dropCreateStrategy ---------");
        DatabaseManager.getInstance().dropCreateStrategy();
        AdminFacadeTest.adminLogin();
        AdminFacadeTest.addCompany(getCompany1());
        AdminFacadeTest.addCompany(getCompany2());
        // try to add exist same company name
        Company company3 = getCompany3();
        company3.setName(getCompany1().getName());
        AdminFacadeTest.addCompany(company3);
        // try to add exist same company email
        Company company4 = getCompany3();
        company4.setEmail(getCompany1().getEmail());
        AdminFacadeTest.addCompany(company4);
        System.out.println("----- Main Tests END -----");
    }


    public static Company getCompany1() {
        return new Company(1, "company1", "company1@gmail.com", "company1_password");
    }

    public static Company getCompany2() {
        return new Company(2, "company2", "company2@gmail.com", "company2_password");
    }

    public static Company getCompany3() {
        return new Company(3, "company3", "company3@gmail.com", "company3_password");
    }

    public static Customer getCustomer1() {
        return new Customer(1,"customer1", "customer1Family", "customer1@gmail.com", "customer1_password");
    }

    public static Customer getCustomer2() {
        return new Customer(2,"customer2", "customer2Family", "customer2@gmail.com", "customer3_password");
    }

    public static Customer getCustomer3() {
        return new Customer(3,"customer3", "customer3Family", "customer3@gmail.com", "customer3_password");
    }
}
