package facade;


import exceptions.LoginException;

public class AdminTest {
    public static void test() throws LoginException {
        ClientFacade admin = new AdminFacade();
        if (admin.login("admin@admin.com", "admin")) {
            System.out.println("* Welcome Admin *");
        }
    }
}
