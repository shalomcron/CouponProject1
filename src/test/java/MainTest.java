import dao.*;
import db.DatabaseManager;
import exceptions.JDBCException;
import facade.AdminFacadeTest;
import facade.CompanyFacadeTest;
import facade.CustomerFacadeTest;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("----- Main Tests START -----");
        System.out.println("---------- dropCreateStrategy ---------");
        DatabaseManager.getInstance().dropCreateStrategy();
        // DAOTests();
        // System.out.println("\n");
        System.out.println("\n ---------- adminFacadeTest START ---------");
        AdminFacadeTest.adminTest();
        System.out.println("---------- adminFacadeTest END ---------");
        System.out.println("\n ---------- companyFacadeTest START ---------");
        CompanyFacadeTest.companyTest();
        System.out.println(" ---------- companyFacadeTest END ---------");
        System.out.println("\n ---------- customerFacadeTest START ---------");
        CustomerFacadeTest.customerTest();
        System.out.println(" ---------- customerFacadeTest END ---------");
        System.out.println("\n ----- Main Tests END -----");
    }



    private static void DAOTests() {
        try {
            System.out.println("---------- DAOTests START ---------");
            CompaniesTests.test();
            CustomersTest.test();
            CategoriesTest.test();
            CouponTest.test();
            System.out.println("---------- DAOTests END ---------");
        } catch (JDBCException e) {
            System.out.println("DAOTests Exception: " + e.getMessage());
        }
    }


}
