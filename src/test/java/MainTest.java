import beans.cliens.Company;
import beans.cliens.Customer;
import db.DatabaseManager;
import facade.clients.AdminFacade;
import facade.clients.CompanyFacade;
import facade.clients.CustomerFacade;
import facade.login.LoginManager;

public class MainTest {
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static AdminFacade adminFacade = null;
    private static CompanyFacade companyFacade1, companyFacade2, companyFacade3;
    private static CustomerFacade customerFacade1, customerFacade2, customerFacade3;

    private static Company company1 = new Company(1, "company1", "company1@gmail.com", "company1_password");
    private static Company company2 = new Company(2, "company2", "company2@gmail.com", "company2_password");
    private static Customer customer1 = new Customer(1,"customer1", "customer1Family", "customer1@gmail.com", "customer1_password");
    private static Customer customer2 = new Customer(2,"customer2", "customer2Family", "customer2@gmail.com", "customer3_password");
    private static Customer customer3 = new Customer(3,"customer3", "customer3Family", "customer3@gmail.com", "customer3_password");

    public static void main(String[] args) {
        System.out.println("----- Main Tests START -----");
        System.out.println("---------- dropCreateStrategy ---------");
        DatabaseManager.getInstance().dropCreateStrategy();
        adminFacade = adminLogin();
        System.out.println("----- Main Tests END -----");
    }

    private static AdminFacade adminLogin() {

    }

}
