package facade.login;

import exceptions.LoginException;
import facade.AdminFacade;
import facade.ClientFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;

public class LoginManager {
    private static LoginManager instance = null;

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }

    public ClientFacade login(String email, String password, ClientType clientType) {
        ClientFacade res = null;
        try {
            switch (clientType) {
                case Admin:
                    res = new AdminFacade();
                    break;
                case Company:
                    res = new CompanyFacade();
                    break;
                case Customer:
                    res = new CustomerFacade();
                    break;
            }
            if (!res.login(email, password)) {
                throw new LoginException(clientType);
            }
        } catch (LoginException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return res;
    }

}
