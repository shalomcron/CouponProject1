package facade.clients;

import beans.cliens.Company;
import beans.cliens.Customer;
import exceptions.CompanyException;
import exceptions.CompanyMsg;
import exceptions.JDBCException;

import java.util.List;

public class AdminFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    public void addCompany(Company company) throws JDBCException, CompanyException {
        if (companyDAO.isExistByName(company.getName())) {
            throw new CompanyException(CompanyMsg.COMPANY_NAME_ALREADY_EXIST);
        }
        if (companyDAO.isExistByEmail(company.getName())) {
            throw new CompanyException(CompanyMsg.COMPANY_EMAIL_ALREADY_EXIST);
        }
        companyDAO.add(company);
    }

    public void updateCompany(int id, Company companyToUpdate) throws JDBCException, CompanyException {
        Company existCompany = companyDAO.getSingle(id);
        if (existCompany == null) {
            throw new CompanyException(CompanyMsg.COMPANY_NOT_EXIST);
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
