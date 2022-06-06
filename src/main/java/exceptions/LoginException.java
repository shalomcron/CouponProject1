package exceptions;

import facade.clients.ClientType;

public class LoginException extends Exception {

    public LoginException(ClientType clientType) {
        super("Fail log in for client " + clientType.name());
    }
}
