package facade.clients;

import beans.cliens.Company;
import exceptions.ClientExistException;
import exceptions.ClientNotExistException;
import exceptions.JDBCException;

public class AdminFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    public void addCompany(Company company) throws JDBCException, ClientExistException {
        if (companyDAO.isExist(company.getEmail(), company.getPassword())) {
            throw new ClientExistException(ClientType.Company, company.getEmail(), company.getPassword());
        }
        companyDAO.add(company);
    }

    public void updateCompany(Company companyToUpdate) throws JDBCException, ClientNotExistException {
        Company existCompany = companyDAO.getSingle(companyToUpdate.getId());
        if (existCompany == null) {
            throw new ClientNotExistException(ClientType.Company, companyToUpdate.getId());
        }
    }
}
