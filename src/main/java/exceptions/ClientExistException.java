package exceptions;

import facade.clients.ClientType;

public class ClientExistException extends Exception {
    public ClientExistException(ClientType clientType, String email, String password) {
        super("Client " + clientType.name() + " email:" + email + " password:" + password + " already exist !");
    }
}
