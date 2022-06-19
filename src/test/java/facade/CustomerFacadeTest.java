package facade;
import beans.cliens.Customer;
import facade.clients.ClientType;
import facade.clients.CustomerFacade;
import facade.login.LoginManager;

public class CustomerFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();

    public static CustomerFacade login(Customer company) {
        return (CustomerFacade) loginManager.login(company.getEmail(), company.getPassword(), ClientType.Customer);
    }
}
