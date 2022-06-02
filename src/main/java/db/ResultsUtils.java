package db;

import beans.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kobis on 15 May, 2022
 */
public class ResultsUtils {
    public static Company companyFromRow(Map<String, Object> map) {
        int id = (int) map.get("ID");
        String name = (String) map.get("NAME");
        String email = (String) map.get("EMAIL");
        String password = (String) map.get("PASSWORD");
        return new Company(id, name, email, password);
    }

    // int id, String firstName, String lastName, String email, String password
    public static Customer customerFromRow(Map<String, Object> map) {
        int id = (int) map.get("ID");
        String firstName = (String) map.get("FIRST_NAME");
        String lastName = (String) map.get("LAST_NAME");
        String email = (String) map.get("EMAIL");
        String password = (String) map.get("PASSWORD");
        return new Customer(id, firstName, lastName, email, password);
    }

    public static Category categoryFromRow(Map<String, Object> map) {
        int id = (int) map.get("ID");
        String name = (String) map.get("NAME");
        return Category.valueOf(name);
    }

    public static Coupon couponFromRow(Map<String, Object> map) {
        int id = (int) map.get("ID"), companyId = (int) map.get("ID_COMPANY"),
                categoryId = (int) map.get("ID_CATEGORY"), amount = (int) map.get("AMOUNT");
        String title = (String) map.get("TITLE"), description = (String) map.get("DESCRIPTION"),
                image = (String) map.get("IMAGE");
        Date startDate = (Date) map.get("DATE_START"), endDate = (Date) map.get("DATE_END");
        double price = (double) map.get("PRICE");
        return new Coupon(id, companyId, categoryId, title, description, startDate, endDate, amount, price, image);
    }

//    public static CouponsVsCustomers couponsVsCustomers(Map<String, Object> map) {
//        int customerId = (int) map.get("ID_CUSTOMER");
//        int couponId = (int) map.get("ID_COUPON");
//        return new CouponsVsCustomers(customerId, couponId);
//    }

    public static boolean isExistFromRow(Map<String, Object> map) {
        Long res = (Long) map.get("RES");
        return res == 1;
    }
}
