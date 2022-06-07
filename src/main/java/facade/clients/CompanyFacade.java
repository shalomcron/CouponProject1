package facade.clients;

import beans.cliens.Company;
import beans.coupone.Coupon;
import exceptions.JDBCException;

public class CompanyFacade extends ClientFacade {
    private int companyId;

    @Override
    public boolean login(String email, String password) {
        try {
            Company company = companyDAO.getSingle(email, password);
            if (company != null) {
                this.companyId = company.getId();
                return true;
            }
        } catch (JDBCException e) {
            System.out.println("login -" + e);
        }
        return false;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void addCoupon(Coupon coupon) throws JDBCException {
        coupon.setCompanyId(this.getCompanyId());
        couponDAO.add(coupon);
    }
}
