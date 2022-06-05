package exceptions;

import facade.clients.ClientType;

public class LoginException extends Exception {

    public LoginException(ClientType clientType) {
        super("LoginException for client " + clientType.name());
    }
}
