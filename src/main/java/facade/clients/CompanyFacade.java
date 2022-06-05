package facade.clients;

import exceptions.LoginException;

public class CompanyFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) throws LoginException {
        // return false;
        throw new LoginException(ClientType.Company);
    }
}
