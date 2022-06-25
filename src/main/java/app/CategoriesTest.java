package app;

import beans.coupone.Category;
import dao.category.CategoryDAO;
import dao.category.CategoryDAOImpl;
import exceptions.JDBCException;

public class CategoriesTest {
    private static final CategoryDAO categoryDAO = CategoryDAOImpl.getInstance();

    public static void test() throws JDBCException {
            System.out.println("*************************");
            System.out.println("*** testCrudCategories ***");
            System.out.println("*************************");
            insertCategories();
            getAllCategories();
    }

    public static void insertCategories() throws JDBCException {
        System.out.println("---------- insertCategories TEST ---------");
        for (Category c: Category.values()) {
            categoryDAO.add(c);
        }
    }

    public static void getAllCategories() throws JDBCException {
        System.out.println("---------- getAllCategories TEST ---------");
        categoryDAO.getAll().forEach(System.out::println);
    }

//    public static void getSingleCategory() throws JDBCException {
//        getSingleCategory(1);
//    }

//    public static void getSingleCategory(int id) throws JDBCException {
//        System.out.println("---------- getSingleCategory for ID:" + id + " TEST ---------");
//        System.out.println(categoryDAO.getSingle(id));
//    }

//    public static void updateCategory() throws JDBCException {
//        int id = 2;
//        System.out.println("Before update Category");
//        getSingleCategory(id);
//        Category category = new Category("update Category");
//        categoryDAO.update(id, category);
//        System.out.println("After company Category");
//        getSingleCategory(id);
//    }

//    public static void deleteCategory() throws JDBCException {
//        int id = 3;
//        System.out.println("Before delete Category " + id);
//        getAllCategories();
//        categoryDAO.delete(id);
//        System.out.println("After delete Category " + id);
//        getAllCategories();
//    }

}
