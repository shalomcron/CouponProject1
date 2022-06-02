package facade;

import exceptions.LoginException;

public class AdminFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) throws LoginException {
        // TODO: USE CONDOTION
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            return true;
        }
        throw new LoginException("Not admin found for email:" + email + " and password:" + password);
    }
}
