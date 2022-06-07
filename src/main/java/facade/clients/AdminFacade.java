package facade.clients;

import beans.cliens.Company;
import beans.cliens.Customer;
import exceptions.ClientExistException;
import exceptions.ClientNotExistException;
import exceptions.JDBCException;

import java.util.List;

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
        int id = companyToUpdate.getId();
        Company existCompany = companyDAO.getSingle(id);
        if (existCompany == null) {
            throw new ClientNotExistException(ClientType.Company, companyToUpdate.getId());
        }
        companyDAO.update(id, companyToUpdate);
    }

    public Company geSingleCompany(String email, String password) throws JDBCException {
        return companyDAO.getSingle(email, password);
    }

    public Customer geSingleCustomer(String email, String password) throws JDBCException {
        return customerDAO.getSingle(email, password);
    }

    public void deleteCompany(int id) throws JDBCException {
        companyDAO.delete(id);
    }

    public List<Company> getAllCompanies() throws JDBCException {
        return companyDAO.getAll();
    }

    public Company getOneCompany(int id) throws JDBCException {
        return companyDAO.getSingle(id);
    }

    public void addCustomer(Customer customer) throws JDBCException {
        customerDAO.add(customer);
    }

    public void updateCustomer(Customer customer) throws JDBCException {
        customerDAO.update(customer.getId(), customer);
    }

    public void deleteCustomer(Customer customer) throws JDBCException {
        customerDAO.delete(customer.getId());
    }

    public List<Customer> getAllCustomers() throws JDBCException {
        return customerDAO.getAll();
    }

    public Customer geSingleCompany(int id) throws JDBCException {
        return customerDAO.getSingle(id);
    }
}
