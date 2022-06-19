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

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MainTest {
    private static final int AMOUNT_COUPONS = 2;
    private static int counterCoupone = 1;
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

        // try to add exist same company name
        Company companyAddSameId = getCompany4();
        companyAddSameId.setName(getCompany1().getName());
        AdminFacadeTest.addCompany(companyAddSameId);

        // try to add exist same company email
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
        AdminFacadeTest.deleteCompany(getCompany4());
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
        AdminFacadeTest.deleteCustomer(getCustomer4().getId());
        AdminFacadeTest.getAllCustomers();
        AdminFacadeTest.getOneCustomer(getCustomer1().getId());

        System.out.println("---------- CustomerFacadeTest - login with customer 1 ---------");
        companyFacade1 = CompanyFacadeTest.login(getCompany1());
        System.out.println("companyFacade1" + companyFacade1);
        if (companyFacade1 != null) {
            int companyId = companyFacade1.getCompanyId();
            Coupon coupon1 = getCoupon1(counterCoupone++, companyId);
            CompanyFacadeTest.addCoupons(companyFacade1, coupon1);
            Coupon coupon2 = getCoupon2(counterCoupone++, companyId);
            CompanyFacadeTest.addCoupons(companyFacade1, coupon2);
            Coupon coupon3 = getCoupon3(counterCoupone++, companyId);
            CompanyFacadeTest.addCoupons(companyFacade1, coupon3);
            coupon1.setTitle("TITLE UPDATED");
            CompanyFacadeTest.updateCoupon(companyFacade1, coupon1.getId(), coupon1);
            System.out.println("* trying to update coupon id");
            coupon1.setId(2);
            CompanyFacadeTest.updateCoupon(companyFacade1,1, coupon1);
            System.out.println("* trying to update coupon compant id");
            coupon1.setId(1);
            coupon1.setCompanyId(3);
            CompanyFacadeTest.updateCoupon(companyFacade1, coupon1.getId(), coupon1);
            CompanyFacadeTest.deleteCoupon(companyFacade1, coupon1.getId());

        }
        companyFacade2 = CompanyFacadeTest.login(getCompany2());
        System.out.println("companyFacade2" + companyFacade2);
        if (companyFacade2 != null) {
            int companyId = companyFacade2.getCompanyId();
            Coupon coupon1 = getCoupon1(counterCoupone++, companyId);
            CompanyFacadeTest.addCoupons(companyFacade2, coupon1);
            Coupon coupon2 = getCoupon2(counterCoupone++, companyId);
            CompanyFacadeTest.addCoupons(companyFacade2, coupon2);
            Coupon coupon3 = getCoupon3(counterCoupone++, companyId);
            CompanyFacadeTest.addCoupons(companyFacade2, coupon3);
            CompanyFacadeTest.getAllCoupons(companyFacade2);
            CompanyFacadeTest.getAllCategoryCoupons(companyFacade2, Category.Restaurant);
            CompanyFacadeTest.getAllMaxPriceCoupons(companyFacade2, 10);
            CompanyFacadeTest.getCompanyDetails(companyFacade2);
        }


        System.out.println("---------- CustomerFacadeTest - login with customer 1 ---------");
        customerFacade1 = CustomerFacadeTest.login(getCustomer1());
        if (customerFacade1 != null) {
            CustomerFacadeTest.purchaseCoupon(customerFacade1, getCoupon2(2, companyFacade2.getCompanyId()));
            CustomerFacadeTest.purchaseCoupon(customerFacade1, getCoupon3(3, companyFacade2.getCompanyId()));
        }

        System.out.println("---------- CustomerFacadeTest - login with customer 2 ---------");
        customerFacade2 = CustomerFacadeTest.login(getCustomer2());
        if (customerFacade2 != null) {
            CustomerFacadeTest.purchaseCoupon(customerFacade2, getCoupon2(2, companyFacade2.getCompanyId()));
            System.out.println("* trying to purchase no exist coupon");
            CustomerFacadeTest.purchaseCoupon(customerFacade1, getCoupon1(1, companyFacade2.getCompanyId()));
            System.out.println("* trying to purchase same coupon");
            CustomerFacadeTest.purchaseCoupon(customerFacade2, getCoupon2(2, companyFacade2.getCompanyId()));
            System.out.println("* trying to purchase coupon with amount 0");
            CustomerFacadeTest.purchaseCoupon(customerFacade2, getCoupon3(3, companyFacade2.getCompanyId()));
        }

        System.out.println("----- Main Tests END -----");
    }

    private static Coupon getCoupon1(int couponId, int companyId) {
        return new Coupon(couponId, companyId, Category.Food.getId(), "גבינות", "קופון 10% הנחה על גבינות השמנת",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plus(1, ChronoUnit.MONTHS)), AMOUNT_COUPONS, 5, "image");
    }
    private static Coupon getCoupon2(int couponId, int companyId) {
        return new Coupon(couponId, companyId, Category.Restaurant.getId(), "מסעדה", "קופון 60% למסעדה",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plus(1, ChronoUnit.MONTHS)), AMOUNT_COUPONS, 10, "image");
    }
    private static Coupon getCoupon3(int couponId, int companyId) {
        return new Coupon(couponId, companyId, Category.Vacation.getId(), "טיול", "קופון 50% לטיול באירופה",
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
        return new Customer(1,"customer1", "customer1Family", "customer1@gmail.com", "customer1_password");
    }

    public static Customer getCustomer2() {
        return new Customer(2,"customer2", "customer2Family", "customer2@gmail.com", "customer3_password");
    }

    public static Customer getCustomer3() {
        return new Customer(3,"customer3", "customer3Family", "customer3@gmail.com", "customer3_password");
    }
    public static Customer getCustomer4() {
        return new Customer(4,"customer4", "customer4Family", "customer4@gmail.com", "customer4_password");
    }
}
