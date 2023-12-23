package org.eSHOP;

import org.eSHOP.manager.CategoryManager;
import org.eSHOP.manager.ProductManager;
import org.eSHOP.model.Category;
import org.eSHOP.model.Product;
import org.eSHOP.util.Commands;

import java.util.Scanner;


public class Main implements Commands {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final CategoryManager CATEGORY_MENEGER = new CategoryManager();
    private static final ProductManager PRODUCT_MENEGER = new ProductManager();


    public static void main(String[] args) {
        Commands.commands();
        String command = SCANNER.nextLine();
        boolean isRun = true;
        while (isRun) {
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_CATEGORY:
                    addCategory();
                    break;
                case EDIT_CATEGORY_BY_ID:
                    editCategoryById();
                    break;
                case DELETE_CATEGORY_BY_ID:
                    deleteCategoryById();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case EDIT_PRODUCT_BY_ID:
                    editProductById();
                    break;
                case DELETE_PRODUCT_BY_ID:
                    deleteProductById();
                    break;
                case PRINT_SUM_OF_PRODUCTS:
                    PRODUCT_MENEGER.printSumOfProducts();
                    break;
                case PRINT_MAX_OF_PRICE_PRODUCTS:
                    PRODUCT_MENEGER.printMaxOfPriceProduct();
                    break;
                case PRINT_MIN_OF_PRICE_PRODUCTS:
                    PRODUCT_MENEGER.printMinOfPriceProduct();
                    break;
                case PRINT_AVG_OF_PRICE_PRODUCTS:
                    PRODUCT_MENEGER.printAvgOfPriceProduct();
                    break;
                default:
                    System.out.println("wrong command try again");
            }

        }


    }


    private static void deleteProductById() {
        System.out.println("please input id for delete product");
        PRODUCT_MENEGER.getAllProducts();
        int id = 0;
        try {
            id = Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        PRODUCT_MENEGER.deleteProductById(id);
    }

    private static void editProductById() {
        System.out.println("please input product id for edit");
        int id = 0;
        try {
            id = Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Product productById = PRODUCT_MENEGER.getProductById(id);
        if (productById == null) {
            System.out.println("Wrong id try again");
            return;
        }
        System.out.println("please input product name");
        String name = SCANNER.nextLine();
        System.out.println("please input product description");
        String description = SCANNER.nextLine();
        System.out.println("please input product price");
        double price = 0;
        int qty = 0;
        int categoryId = 0;
        Category categoryById = null;
        try {
            price = Double.parseDouble(SCANNER.nextLine());
            System.out.println("please input product quantity");
            qty = Integer.parseInt(SCANNER.nextLine());
            System.out.println("please input category id");
            CATEGORY_MENEGER.getAllCategories();
            categoryId = Integer.parseInt(SCANNER.nextLine());
            categoryById = CATEGORY_MENEGER.getCategoryById(categoryId);
            if (categoryById == null) {
                System.out.println("Wrong category id try again");
                return;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        productById.setName(name);
        productById.setDescription(description);
        productById.setPrice(price);
        productById.setQuantity(qty);
        productById.setCategory(categoryById);
        PRODUCT_MENEGER.editProductById(productById);
    }

    private static void addProduct() {
        System.out.println("please input product name");
        String name = SCANNER.nextLine();
        System.out.println("please input product description");
        String description = SCANNER.nextLine();
        System.out.println("please input product price");
        double price = 0;
        int qty = 0;
        int categoryId = 0;
        Category categoryById = null;
        try {
            price = Double.parseDouble(SCANNER.nextLine());
            System.out.println("please input product quantity");
            qty = Integer.parseInt(SCANNER.nextLine());
            System.out.println("please input category id");
            CATEGORY_MENEGER.getAllCategories();
            categoryId = Integer.parseInt(SCANNER.nextLine());
            categoryById = CATEGORY_MENEGER.getCategoryById(categoryId);
            if (categoryById == null) {
                System.out.println("Wrong category id try again");
                return;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Product product = new Product(name, description, price, qty, categoryById);
        PRODUCT_MENEGER.addProduct(product);
    }

    private static void deleteCategoryById() {
        System.out.println("please input id for delete category");
        CATEGORY_MENEGER.getAllCategories();
        int id = 0;
        try {
            id = Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        CATEGORY_MENEGER.deleteCategoryById(id);
    }


    public static void editCategoryById() {
        System.out.println("please input id for edit by category");
        CATEGORY_MENEGER.getAllCategories();
        int id = 0;
        try {
            id = Integer.parseInt(SCANNER.nextLine());

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Category categoryById = CATEGORY_MENEGER.getCategoryById(id);
        if (categoryById == null) {
            System.out.println("Wrong id try again");
            return;
        }
        System.out.println("please input category name");
        String categoryName = SCANNER.nextLine();
        categoryById.setName(categoryName);
        CATEGORY_MENEGER.editCategoryById(categoryById);
    }

    private static void addCategory() {
        System.out.println("please input category name");
        String categoryName = SCANNER.nextLine();
        Category category = new Category(categoryName);
        CATEGORY_MENEGER.addCategory(category);
    }
}













