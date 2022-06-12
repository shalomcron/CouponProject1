package facade.store;

import beans.cliens.Customer;

public class CustomerStore {
    private static Customer yosef = new Customer("Yosef", "YosefFamily", "yosef@gmail.com", "yosef_password");
    private static Customer david = new Customer("David", "DavidFamily", "david@gmail.com", "david_password");
    private static Customer emailLikeDavid = new Customer("emailLikeDavid", "emailLikeDavidFamily", "david@gmail.com", "david_password");

    public static Customer getEmailLikeDavid() {
        return emailLikeDavid;
    }

    public static void setEmailLikeDavid(Customer emailLikeDavid) {
        CustomerStore.emailLikeDavid = emailLikeDavid;
    }


    public static Customer getYosef() {
        return yosef;
    }

    public static void setYosef(Customer yosef) {
        CustomerStore.yosef = yosef;
    }

    public static Customer getDavid() {
        return david;
    }

    public static void setDavid(Customer david) {
        CustomerStore.david = david;
    }
}
