package exceptions;

import facade.login.ClientType;

public class LoginException extends Exception {

    public LoginException(ClientType clientType) {
        super("LoginException for client " + clientType.name());
    }
}
