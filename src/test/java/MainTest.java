import beans.cliens.Company;
import beans.cliens.Customer;
import beans.coupone.Category;
import beans.coupone.Coupon;
import db.DatabaseManager;
import facade.AdminFacadeTest;
import facade.CompanyFacadeTest;
import facade.CustomerFacadeTest;
import facade.clients.CompanyFacade;
import facade.clients.CustomerFacade;
import jobs.DeleteExpiredCoupons;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MainTest {
    private static final int AMOUNT_COUPONS = 1;
    private static CompanyFacade companyFacade1, companyFacade2;
    private static CustomerFacade customerFacade1, customerFacade2;

    public static void main(String[] args) {
        System.out.println("----- Main Tests START -----");
        System.out.println("---------- dropCreateStrategy ---------");
        DatabaseManager.getInstance().dropCreateStrategy();
        System.out.println("---------- AdminFacadeTest - Company ---------");
        AdminFacadeTest.adminLogin();
        AdminFacadeTest.addCompany(getCompany1());
        AdminFacadeTest.addCompany(getCompany2());
        AdminFacadeTest.addCompany(getCompany3());

        System.out.println("* try to add exist same company name");
        Company companyAddSameId = getCompany4();
        companyAddSameId.setName(getCompany1().getName());
        AdminFacadeTest.addCompany(companyAddSameId);

        System.out.println("* try to add exist same company email");
        Company companyAddSameEmail = getCompany3();
        companyAddSameEmail.setEmail(getCompany1().getEmail());
        AdminFacadeTest.addCompany(companyAddSameEmail);

        // updateCompany
        Company companyToUpdate = getCompany3();
        companyToUpdate.setEmail("getCompany3 new email");
        companyToUpdate.setPassword("getCompany3 new password");
        AdminFacadeTest.updateCompany(companyToUpdate.getId(), companyToUpdate);
        System.out.println("* trying to update id");
        companyToUpdate.setId(8);
        AdminFacadeTest.updateCompany(getCompany1().getId(), companyToUpdate);
        System.out.println("* trying to update name");
        companyToUpdate = getCompany1();
        companyToUpdate.setName("bla");
        AdminFacadeTest.updateCompany(companyToUpdate.getId(), companyToUpdate);

        AdminFacadeTest.addCompany(getCompany4());
        AdminFacadeTest.getAllCompanies();
        AdminFacadeTest.getOneCompany(getCompany3());

        System.out.println("---------- AdminFacadeTest - Customer ---------");
        AdminFacadeTest.addCustomer(getCustomer1());
        AdminFacadeTest.addCustomer(getCustomer2());
        AdminFacadeTest.addCustomer(getCustomer3());
        System.out.println("* trying to add customer with exixiting email");
        Customer customerToUpdateName = getCustomer4();
        customerToUpdateName.setEmail(getCustomer1().getEmail());
        AdminFacadeTest.addCustomer(customerToUpdateName);

        Customer customerToUpdate = getCustomer3();
        customerToUpdate.setPassword("new password for customer 3");
        AdminFacadeTest.updateCustomer(customerToUpdate.getId(), customerToUpdate);
        System.out.println("* trying to update customer id");
        Customer customerToUpdateId = getCustomer3();
        customerToUpdateId.setId(getCustomer1().getId());
        AdminFacadeTest.updateCustomer(getCustomer3().getId(), customerToUpdateId);
        AdminFacadeTest.addCustomer(getCustomer4());
        AdminFacadeTest.getAllCustomers();
        AdminFacadeTest.getOneCustomer(getCustomer1().getId());

        System.out.println("---------- CompanyFacadeTest - login with company 1 ---------");
        companyFacade1 = CompanyFacadeTest.login(getCompany1());
        if (companyFacade1 != null) {
            int companyId = companyFacade1.getCompanyId();
            CompanyFacadeTest.addCoupons(companyFacade1, getCompany1Coupon1(companyId));
            CompanyFacadeTest.addCoupons(companyFacade1, getCompany1Coupon2(companyId));
            CompanyFacadeTest.addCoupons(companyFacade1, getCompany1Coupon3(companyId));

            Coupon coupon1 = getCompany2Coupon2(companyFacade1.getCompanyId());
            coupon1.setTitle("TITLE UPDATED");
            CompanyFacadeTest.updateCoupon(companyFacade1, coupon1.getId(), coupon1);

            System.out.println("* trying to update coupon company id");
            coupon1.setCompanyId(3);
            CompanyFacadeTest.updateCoupon(companyFacade1, coupon1.getId(), coupon1);

            System.out.println("* trying to update coupon id");
            CompanyFacadeTest.updateCoupon(companyFacade1, 111, getCompany2Coupon2(companyFacade1.getCompanyId()));

            CompanyFacadeTest.getAllCoupons(companyFacade1);
            CompanyFacadeTest.getAllCategoryCoupons(companyFacade1, Category.Restaurant);
            CompanyFacadeTest.getAllMaxPriceCoupons(companyFacade1, 10);
            CompanyFacadeTest.getCompanyDetails(companyFacade1);
        }


        System.out.println("---------- CompanyFacadeTest - login with company 2 ---------");
        companyFacade2 = CompanyFacadeTest.login(getCompany2());
        if (companyFacade2 != null) {
            int companyId = companyFacade2.getCompanyId();
            CompanyFacadeTest.addCoupons(companyFacade2, getCompany2Coupon1(companyId));
            CompanyFacadeTest.addCoupons(companyFacade2, getCompany2Coupon2(companyId));
            CompanyFacadeTest.addCoupons(companyFacade2, getCompany2Coupon3(companyId));
        }

        System.out.println("---------- CustomerFacadeTest - login with customer 1 ---------");
        customerFacade1 = CustomerFacadeTest.login(getCustomer1());
        if (customerFacade1 != null) {
            CustomerFacadeTest.purchaseCoupon(customerFacade1, getCompany1Coupon1(companyFacade1.getCompanyId()));
            CustomerFacadeTest.purchaseCoupon(customerFacade1, getCompany1Coupon2(companyFacade1.getCompanyId()));
            CustomerFacadeTest.purchaseCoupon(customerFacade1, getCompany1Coupon3(companyFacade1.getCompanyId()));
            System.out.println("get customer 2 Purchases");
            CustomerFacadeTest.getPurchasedCoupons(customerFacade1);
            CustomerFacadeTest.getPurchasedCoupons(customerFacade1, Category.Vacation);
        }

        System.out.println("---------- CustomerFacadeTest - login with customer 2 ---------");
        customerFacade2 = CustomerFacadeTest.login(getCustomer2());
        if (customerFacade2 != null) {
            CustomerFacadeTest.purchaseCoupon(customerFacade2, getCompany2Coupon1(companyFacade2.getCompanyId()));
            CustomerFacadeTest.purchaseCoupon(customerFacade2, getCompany2Coupon2(companyFacade2.getCompanyId()));
            CustomerFacadeTest.purchaseCoupon(customerFacade2, getCompany2Coupon3(companyFacade2.getCompanyId()));
            System.out.println("get customer 2 Purchases");
            CustomerFacadeTest.getPurchasedCoupons(customerFacade2);
            CustomerFacadeTest.getPurchasedCoupons(customerFacade2, Category.Vacation);

            System.out.println("* trying to purchase no exist coupon");
            CustomerFacadeTest.purchaseCoupon(customerFacade2, getCouponNotExist());

            System.out.println("* trying to purchase coupon with amount 0");
            // "Company 1 Coupon 1" was purchased by customer 1
            CustomerFacadeTest.purchaseCoupon(customerFacade2, getCompany1Coupon1(companyFacade1.getCompanyId()));
        }


        System.out.println("delete tests");
        AdminFacadeTest.deleteCompany(getCompany4());
        CompanyFacadeTest.deleteCoupon(companyFacade1, getCompany1Coupon1(companyFacade1.getCompanyId()).getId());
        AdminFacadeTest.deleteCustomer(getCustomer2().getId());

        System.out.println("---------- DeleteExpiredCoupons ---------");
        DeleteExpiredCoupons deleteExpiredCoupons = new DeleteExpiredCoupons();
        new Thread(deleteExpiredCoupons).start();


        // simulate program running
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        deleteExpiredCoupons.stop();
        System.out.println("----- Main Tests END -----");
    }

    private static Coupon getCouponNotExist() {
        return new Coupon(111, 111, Category.Food.getId(), "Coupon Not Exist", "קופון 10% הנחה על גבינות השמנת",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plus(1, ChronoUnit.MONTHS)), AMOUNT_COUPONS, 5, "image");
    }


    /**
     * Company1
     */
    private static Coupon getCompany1Coupon1(int companyId) {
        return new Coupon(1, companyId, Category.Food.getId(), "Company 1 Coupon 1", "קופון 10% הנחה על גבינות השמנת",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plus(1, ChronoUnit.MONTHS)), AMOUNT_COUPONS, 5, "image");
    }

    private static Coupon getCompany1Coupon2(int companyId) {
        return new Coupon(2, companyId, Category.Restaurant.getId(), "Company 1 Coupon 2", "קופון 60% למסעדה",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plus(1, ChronoUnit.MONTHS)), AMOUNT_COUPONS, 10, "image");
    }

    private static Coupon getCompany1Coupon3(int companyId) {
        return new Coupon(3, companyId, Category.Vacation.getId(), "Company 1 Coupon 3", "קופון 50% לטיול באירופה",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plus(1, ChronoUnit.MONTHS)), 1, 15, "image");
    }


    /**
     * Company2
     */
    private static Coupon getCompany2Coupon1(int companyId) {
        return new Coupon(4, companyId, Category.Vacation.getId(), "Company 2 Coupon 1", "קופון 50% לטיול באירופה",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plus(1, ChronoUnit.MONTHS)), 1, 15, "image");
    }

    private static Coupon getCompany2Coupon2(int companyId) {
        return new Coupon(5, companyId, Category.Vacation.getId(), "Company 2 Coupon 2", "קופון 50% לטיול באירופה",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plus(1, ChronoUnit.MONTHS)), 1, 15, "image");
    }

    private static Coupon getCompany2Coupon3(int companyId) {
        return new Coupon(6, companyId, Category.Vacation.getId(), "Company 2 Coupon 3", "קופון 50% לטיול באירופה",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plus(1, ChronoUnit.MONTHS)), 1, 15, "image");
    }

    public static Company getCompany1() {
        return new Company(1, "company1", "company1@gmail.com", "company1_password");
    }

    public static Company getCompany2() {
        return new Company(2, "company2", "company2@gmail.com", "company2_password");
    }

    public static Company getCompany3() {
        return new Company(3, "company3", "company3@gmail.com", "company3_password");
    }

    public static Company getCompany4() {
        return new Company(4, "company4", "company4@gmail.com", "company4_password");
    }

    public static Customer getCustomer1() {
        return new Customer(1, "customer1", "customer1Family", "customer1@gmail.com", "customer1_password");
    }

    public static Customer getCustomer2() {
        return new Customer(2, "customer2", "customer2Family", "customer2@gmail.com", "customer3_password");
    }

    public static Customer getCustomer3() {
        return new Customer(3, "customer3", "customer3Family", "customer3@gmail.com", "customer3_password");
    }

    public static Customer getCustomer4() {
        return new Customer(4, "customer4", "customer4Family", "customer4@gmail.com", "customer4_password");
    }
}
