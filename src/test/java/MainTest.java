import dao.*;
import db.DatabaseManager;
import exceptions.JDBCException;
import facade.FacadeTest;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("----- Main Tests START -----");
        System.out.println("---------- dropCreateStrategy ---------");
        DatabaseManager.getInstance().dropCreateStrategy();
        // DAOTests();
        facadeTest();
        System.out.println("----- Main Tests END -----");
    }

    private static void facadeTest() {
        try {
            System.out.println("---------- facadeTest START ---------");
            FacadeTest.adminTest();
            System.out.println("---------- facadeTest END ---------");
        } catch (Exception e) {
            System.out.println("facadeTest Exception: " + e.getMessage());
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
