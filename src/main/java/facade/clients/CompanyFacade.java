package facade.clients;

import beans.cliens.Company;
import beans.coupone.Category;
import beans.coupone.Coupon;
import exceptions.CouponException;
import exceptions.CouponMsg;
import exceptions.JDBCException;

import java.util.List;

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

    public void addCoupon(Coupon coupon) throws JDBCException, CouponException {
        if (couponDAO.isExistByTitle(this.getCompanyId(), coupon.getTitle())) {
            throw new CouponException(CouponMsg.COUPON_TITLE_ALREADY_EXIST);
        }
        coupon.setCompanyId(this.getCompanyId());
        couponDAO.add(coupon);
    }

    public void updateCoupon(int id, Coupon couponToUpdate) throws JDBCException, CouponException {
        if (id != couponToUpdate.getId()) {
            throw new CouponException(CouponMsg.COUPON_ID_NOT_EDITABLE);
        }
        if (this.getCompanyId() != couponToUpdate.getCompanyId()) {
            throw new CouponException(CouponMsg.COUPON_COMPANY_ID_NOT_EDITABLE);
        }
        couponDAO.update(id, couponToUpdate);
    }

    public void deleteCoupon(int id) throws JDBCException {
        // TODO: DELETE CUSTOMERS COUPONS
        couponDAO.delete(id);
    }

    public List<Coupon> getAllCoupons() throws JDBCException {
        return couponDAO.getAllCompanyCoupons(this.getCompanyId());
    }

    public List<Coupon> getAllCoupons(Category category) throws JDBCException {
        return couponDAO.getAllCompanyCoupons(this.getCompanyId(), category.getId());
    }

    public List<Coupon> getAllCoupons(double maxPrice) throws JDBCException {
        return couponDAO.getAllCompanyCoupons(this.getCompanyId(), maxPrice);
    }
}
