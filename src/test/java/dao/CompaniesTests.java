package dao;

import beans.Company;
import dao.company.CompanyDAO;
import dao.company.CompanyDAOImpl;
import exceptions.JDBCException;

public class CompaniesTests {
    private static final CompanyDAO companyDAO = CompanyDAOImpl.getInstance();

    public static void test() throws JDBCException {
        System.out.println("*************************");
        System.out.println("*** testCrudCompanies ***");
        System.out.println("*************************");
        insertCompanies();
        getAllCompanies();
        getSingleCompany();
        updateCompany();
        // deleteCompany();
        isExistCompany();
    }

    private static void insertCompanies() throws JDBCException {
        System.out.println("---------- insert companies TEST ---------");
        Company company1 = new Company("company1", "company1@gmail.com", "password1");
        Company company2 = new Company("company2", "company2@gmail.com", "password2");
        Company company3 = new Company("company3", "company3@gmail.com", "password3");
        Company company4 = new Company("company4", "company4@gmail.com", "password4");
        Company company5 = new Company("company5", "company5@gmail.com", "password5");
        companyDAO.add(company1);
        companyDAO.add(company2);
        companyDAO.add(company3);
        companyDAO.add(company4);
        companyDAO.add(company5);
    }

    private static void getAllCompanies() throws JDBCException {
        System.out.println("---------- get all companies TEST ---------");
        companyDAO.getAll().forEach(System.out::println);
    }

    private static void getSingleCompany() throws JDBCException {
        getSingleCompany(1);
    }

    private static void getSingleCompany(int id) throws JDBCException {
        System.out.println("---------- get single company for ID:" + id + " TEST ---------");
        System.out.println(companyDAO.getSingle(id));
    }

    private static void updateCompany() throws JDBCException {
        int id = 2;
        System.out.println("Before company update");
        getSingleCompany(id);
        Company company = new Company("company name update", "email update@gmail.com", "password update");
        companyDAO.update(id, company);
        System.out.println("After company update");
        getSingleCompany(id);
    }

    private static void deleteCompany() throws JDBCException {
        int id = 3;
        System.out.println("Before delete company");
        getAllCompanies();
        companyDAO.delete(id);
        System.out.println("After delete company");
        getAllCompanies();
    }

    private static void isExistCompany() throws JDBCException {
        System.out.println("---------- isExistCompany TEST ---------");
        System.out.println(companyDAO.isExist("company1@gmail.com", "password1"));
    }
}
