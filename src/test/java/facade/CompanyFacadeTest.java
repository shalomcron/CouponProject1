package facade;

import beans.cliens.Company;
import beans.coupone.Category;
import beans.coupone.Coupon;
import facade.clients.ClientType;
import facade.clients.CompanyFacade;
import facade.login.LoginManager;

import java.sql.Date;
import java.time.LocalDate;

public class CompanyFacadeTest {
    private static final LoginManager loginManager = LoginManager.getInstance();
    private static Company taraComp = new Company("TARA", "tara@gmail.com", "tara_password");

    // Coupons
    Coupon cheeseCoupon = new Coupon("גבינות", Category.Food.getId(), "קופון 20% הנחה על גבינות השמנת",
            Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 0, 10, "image");
// N            this.companyId = companyId;
// Y this.categoryId = categoryId;
// Y this.title = title;
// Y this.description = description;
// Y this.startDate = startDate;
// Y this.endDate = endDate;
// Y this.amount = amount;
//        this.price = price;
//        this.image = image;

    // קופון 10% הנחה על חלב
    // קופון 5% הנחה על יודורט

    public static void companyTest() {
        CompanyFacade companyFacade = (CompanyFacade) loginManager.login(taraComp.getEmail(), taraComp.getPassword(), ClientType.Company);
        if (companyFacade != null) {
            System.out.println("Company " + taraComp.getName() + " has logged in");
            // companyFacade.addCoupon();
        }
    }
}
