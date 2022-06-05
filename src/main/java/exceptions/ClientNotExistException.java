package exceptions;

import facade.clients.ClientType;

public class ClientNotExistException extends Exception {
    public ClientNotExistException(ClientType clientType, int id) {
        super("Client " + clientType.name() + " id:" + id + " not exist !");
    }
}
