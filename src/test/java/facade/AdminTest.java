package facade;


public class AdminTest {
    public static void test() {
        ClientFacade admin = new AdminFacade();
        if (admin.login("", "")) {
            System.out.println("Welcome Admin");
        }
    }
}
