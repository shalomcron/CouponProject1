package facade;

import dao.company.CompanyDAO;
import dao.company.CompanyDAOImpl;

public abstract class ClientFacade {
    private final CompanyDAO companyDAO = CompanyDAOImpl.getInstance();
}
