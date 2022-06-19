package facade;
import facade.clients.CustomerFacade;
import facade.login.LoginManager;

public class CustomerFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static CustomerFacade customerFacade1, customerFacade2, customerFacade3;
}
