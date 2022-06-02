package facade;

import dao.company.CompanyDAO;
import dao.company.CompanyDAOImpl;
import dao.coupon.CouponDAO;
import dao.coupon.CouponDAOImpl;
import dao.customer.CustomerDAO;
import dao.customer.CustomerDAOImpl;

public abstract class ClientFacade {
    private static final CustomerDAO customerDAO = CustomerDAOImpl.getInstance();
    private static final CompanyDAO companyDAO = CompanyDAOImpl.getInstance();
    private static final CouponDAO couponDAO = CouponDAOImpl.getInstance();

    public abstract boolean login(String email, String password);
}
