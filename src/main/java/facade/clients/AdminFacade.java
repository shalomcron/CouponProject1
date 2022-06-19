package facade.clients;

import beans.cliens.Company;
import beans.cliens.Customer;
import exceptions.*;

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
        if (companyDAO.isExistByEmail(company.getEmail())) {
            throw new CompanyException(CompanyMsg.COMPANY_EMAIL_ALREADY_EXIST);
        }
        companyDAO.add(company);
    }

    public void updateCompany(int id, Company companyToUpdate) throws JDBCException, CompanyException {
        Company companyDromDB = companyDAO.getSingle(id);
        if (companyDromDB == null) {
            throw new CompanyException(CompanyMsg.COMPANY_NOT_EXIST);
        }
        if (!companyDromDB.getName().equals(companyToUpdate.getName())) {
            throw new CompanyException(CompanyMsg.COMPANY_NAME_CANNOT_BE_UPDATED);
        }
        if (id != companyToUpdate.getId()) {
            throw new CompanyException(CompanyMsg.COMPANY_ID_CANNOT_BE_UPDATED);
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
        // TODO: delete company coupon
        // TODO: delete customers coupon
        companyDAO.delete(id);
    }

    public List<Company> getAllCompanies() throws JDBCException {
        return companyDAO.getAll();
    }

    public Company getOneCompany(int id) throws JDBCException {
        return companyDAO.getSingle(id);
    }

    public void addCustomer(Customer customer) throws JDBCException, CustomerException {
        if (customerDAO.isExistByEmail(customer.getEmail())) {
            throw new CustomerException(CustomerMsg.CUSTOMER_EMAIL_EXIST);
        }
        customerDAO.add(customer);
    }

    public void updateCustomer(int id, Customer customerToUpdate) throws JDBCException, CustomerException {
        Customer customerFromDB = customerDAO.getSingle(id);
        if (customerFromDB.getId() != customerToUpdate.getId()) {
            throw new CustomerException(CustomerMsg.CUSTOMER_ID_ALREADY_EXIST);
        }
        customerDAO.update(id, customerToUpdate);
    }

    public void deleteCustomer(Customer customer) throws JDBCException {
        // TODO: DELETE CUSTOMER COUPONS
        customerDAO.delete(customer.getId());
    }

    public List<Customer> getAllCustomers() throws JDBCException {
        return customerDAO.getAll();
    }

    public Customer geSingleCompany(int id) throws JDBCException {
        return customerDAO.getSingle(id);
    }

}
