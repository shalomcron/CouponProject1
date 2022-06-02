package facade;

import exceptions.LoginException;

public class CompanyFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) throws LoginException {
        // return false;
        throw new LoginException("Not company found for email:" + email + " and password:" + password);
    }
}
