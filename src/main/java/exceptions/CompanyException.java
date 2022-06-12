package exceptions;

import facade.clients.ClientType;

public class CompanyException extends Exception {
    public CompanyException(CompanyMsg companyMsg) {
        super(companyMsg.getMsg());
    }
}
