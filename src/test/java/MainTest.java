import dao.*;
import db.DatabaseManager;
import exceptions.JDBCException;

public class MainTest {
    public static void main(String[] args) {
        try {
            System.out.println("---------- MainTest START ---------");
            System.out.println("---------- dropCreateStrategy ---------");
            DatabaseManager.getInstance().dropCreateStrategy();
            CompaniesTests.test();
            CustomersTest.test();
            CategoriesTest.test();
            CouponTest.test();
            CouponsCustomersTest.test();
            System.out.println("---------- MainTest END ---------");
        } catch (JDBCException e) {
            System.out.println(e.getMessage());
        }

    }





}
