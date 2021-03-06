package facade.clients;

import dao.category.CategoryDAO;
import dao.category.CategoryDAOImpl;
import dao.company.CompanyDAO;
import dao.company.CompanyDAOImpl;
import dao.coupon.CouponDAO;
import dao.coupon.CouponDAOImpl;
import dao.couponsCustomers.CouponsCustomersDAO;
import dao.couponsCustomers.CouponsCustomersDAOImpl;
import dao.customer.CustomerDAO;
import dao.customer.CustomerDAOImpl;

public abstract class ClientFacade {
    protected static final CustomerDAO customerDAO = CustomerDAOImpl.getInstance();
    protected static final CompanyDAO companyDAO = CompanyDAOImpl.getInstance();
    protected static final CouponDAO couponDAO = CouponDAOImpl.getInstance();
    protected static final CouponsCustomersDAO couponsCustomersDAO = CouponsCustomersDAOImpl.getInstance();

    public abstract boolean login(String email, String password);
}
