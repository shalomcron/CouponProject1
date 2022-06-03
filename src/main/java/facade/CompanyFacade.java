package facade;

import exceptions.LoginException;
import facade.login.ClientType;

public class CompanyFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) throws LoginException {
        // return false;
        throw new LoginException(ClientType.Company);
    }
}
