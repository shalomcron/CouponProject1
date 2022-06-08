package facade.store;

import beans.cliens.Company;

public class CompanyStore {
    private static Company elalComp = new Company("EL-AL", "elal@gmail.com", "elal_password");
    private static Company zaralComp = new Company("ZARA", "zara@gmail.com", "zara_password");
    private static Company taraComp = new Company("TARA", "tara@gmail.com", "tara_password");

    public static Company getElalComp() {
        return elalComp;
    }

    public static Company getZaralComp() {
        return zaralComp;
    }


    public static Company getTaraComp() {
        return taraComp;
    }

    public static void setElalComp(Company elalComp) {
        CompanyStore.elalComp = elalComp;
    }

    public static void setZaralComp(Company zaralComp) {
        CompanyStore.zaralComp = zaralComp;
    }

    public static void setTaraComp(Company taraComp) {
        CompanyStore.taraComp = taraComp;
    }
}
