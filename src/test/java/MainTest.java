import dao.*;
import db.DatabaseManager;
import exceptions.JDBCException;
import facade.AdminFacadeTest;
import facade.CompanyFacadeTest;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("----- Main Tests START -----");
        System.out.println("---------- dropCreateStrategy ---------");
        DatabaseManager.getInstance().dropCreateStrategy();
        // DAOTests();
        // System.out.println("\n");
        adminFacadeTest();
        System.out.println("\n");
        companyFacadeTest();
        System.out.println("----- Main Tests END -----");
    }

    private static void companyFacadeTest() {
        System.out.println("---------- companyFacadeTest START ---------");
        CompanyFacadeTest.companyTest();
        System.out.println("---------- companyFacadeTest END ---------");
    }

    private static void adminFacadeTest() {
        System.out.println("---------- adminFacadeTest START ---------");
        AdminFacadeTest.adminTest();
        System.out.println("---------- adminFacadeTest END ---------");
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
