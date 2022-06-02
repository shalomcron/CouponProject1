package facade;

import exceptions.LoginException;

public class CustomerFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) throws LoginException {
        // return false;
        throw new LoginException("Not customer found for email:" + email + " and password:" + password);
    }
}
