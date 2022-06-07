import dao.*;
import db.DatabaseManager;
import exceptions.JDBCException;
import facade.AdminFacadeTest;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("----- Main Tests START -----");
        System.out.println("---------- dropCreateStrategy ---------");
        DatabaseManager.getInstance().dropCreateStrategy();
        DAOTests();
        adminFacadeTest();
        System.out.println("----- Main Tests END -----");
    }

    private static void adminFacadeTest() {
        try {
            System.out.println("---------- adminFacadeTest START ---------");
            AdminFacadeTest.adminTest();
            System.out.println("---------- adminFacadeTest END ---------");
        } catch (Exception e) {
            System.out.println("adminFacadeTest Exception: " + e.getMessage());
        }
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
