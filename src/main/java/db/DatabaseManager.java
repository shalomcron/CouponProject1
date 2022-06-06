package db;

/**
 * Created by kobis on 08 May, 2022
 */
public class DatabaseManager {

    private static final DatabaseManager instance = new DatabaseManager();

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    private static final String QUERY_CREATE_SCHEMA = "CREATE SCHEMA `coupone-bhp-386`;";
    private static final String QUERY_DROP_SCHEMA = "DROP DATABASE `coupone-bhp-386`;";
    private static final String QUERY_CREATE_TABLE_COMPANY =
            "CREATE TABLE `coupone-bhp-386`.`companies` (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `NAME` VARCHAR(45) NOT NULL,\n" +
                    "  `EMAIL` VARCHAR(45) NOT NULL,\n" +
                    "  `PASSWORD` VARCHAR(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`),\n" +
                    "  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC) VISIBLE,\n" +
                    "  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE);";
    private static final String QUERY_CREATE_TABLE_CUSTOMERS =
            "CREATE TABLE `coupone-bhp-386`.`customers` (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `FIRST_NAME` VARCHAR(45) NOT NULL,\n" +
                    "  `LAST_NAME` VARCHAR(45) NOT NULL,\n" +
                    "  `EMAIL` VARCHAR(45) NOT NULL,\n" +
                    "  `PASSWORD` VARCHAR(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`),\n" +
                    "  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE);\n";
    private static final String QUERY_CREATE_TABLE_CATEGORIES =
            "CREATE TABLE `coupone-bhp-386`.`categories` (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `NAME` VARCHAR(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`));\n";
    private static final String QUERY_CREATE_TABLE_COUPONS =
            "CREATE TABLE `coupone-bhp-386`.`coupons` (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `ID_COMPANY` INT NOT NULL,\n" +
                    "  `ID_CATEGORY` INT NOT NULL,\n" +
                    "  `TITLE` VARCHAR(45) NOT NULL,\n" +
                    "  `DESCRIPTION` VARCHAR(45) NOT NULL,\n" +
                    "  `DATE_START` DATE NOT NULL,\n" +
                    "  `DATE_END` DATE NOT NULL,\n" +
                    "  `AMOUNT` INT NOT NULL,\n" +
                    "  `PRICE` DOUBLE NOT NULL,\n" +
                    "  `IMAGE` VARCHAR(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`),\n" +
                    "  INDEX `ID_COMPANY_idx` (`ID_COMPANY` ASC) VISIBLE,\n" +
                    "  INDEX `ID_CATEGORY_idx` (`ID_CATEGORY` ASC) VISIBLE,\n" +
                    "  CONSTRAINT `ID_COMPANY`\n" +
                    "    FOREIGN KEY (`ID_COMPANY`)\n" +
                    "    REFERENCES `coupone-bhp-386`.`companies` (`ID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `ID_CATEGORY`\n" +
                    "    FOREIGN KEY (`ID_CATEGORY`)\n" +
                    "    REFERENCES `coupone-bhp-386`.`categories` (`ID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION);\n";

    private static final String QUERY_CREATE_TABLE_COUPONS_CUSTOMERS = "CREATE TABLE `coupone-bhp-386`.`coupons_customers` (\n" +
            "  `ID_CUSTOMER` INT NOT NULL,\n" +
            "  `ID_COUPON` INT NOT NULL,\n" +
            "  PRIMARY KEY (`ID_CUSTOMER`, `ID_COUPON`),\n" +
            "  INDEX `ID_COUPON_idx` (`ID_COUPON` ASC) VISIBLE,\n" +
            "  CONSTRAINT `ID_CUSTOMER`\n" +
            "    FOREIGN KEY (`ID_CUSTOMER`)\n" +
            "    REFERENCES `coupone-bhp-386`.`customers` (`ID`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `ID_COUPON`\n" +
            "    FOREIGN KEY (`ID_COUPON`)\n" +
            "    REFERENCES `coupone-bhp-386`.`coupons` (`ID`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);\n";

    public void dropCreateStrategy() {
        try {
            JDBCUtils.executeQuery(QUERY_DROP_SCHEMA);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            JDBCUtils.executeQuery(QUERY_CREATE_SCHEMA);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_COMPANY);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_CUSTOMERS);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_CATEGORIES);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_COUPONS);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_COUPONS_CUSTOMERS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
