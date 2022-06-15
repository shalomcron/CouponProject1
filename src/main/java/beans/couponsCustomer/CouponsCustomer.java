package beans.couponsCustomer;

public class CouponsCustomer {
    int customerID;
    int couponId;

    public CouponsCustomer(int customerID, int couponId) {
        this.customerID = customerID;
        this.couponId = couponId;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    @Override
    public String toString() {
        return "CouponsCustomer{" +
                "customerID=" + customerID +
                ", couponId=" + couponId +
                '}';
    }
}
