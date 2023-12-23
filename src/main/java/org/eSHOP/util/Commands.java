package org.eSHOP.util;

public interface Commands {
    String EXIT = "0";
    String ADD_CATEGORY = "1";
    String EDIT_CATEGORY_BY_ID = "2";
    String DELETE_CATEGORY_BY_ID = "3";
    String ADD_PRODUCT = "4";
    String EDIT_PRODUCT_BY_ID = "5";
    String DELETE_PRODUCT_BY_ID = "6";
    String PRINT_SUM_OF_PRODUCTS = "7";
    String PRINT_MAX_OF_PRICE_PRODUCTS = "8";
    String PRINT_MIN_OF_PRICE_PRODUCTS = "9";
    String PRINT_AVG_OF_PRICE_PRODUCTS = "10";


    static void commands() {
        System.out.println("please input " + EXIT + " for exit");
        System.out.println("please input " + ADD_CATEGORY + " for print all category");
        System.out.println("please input " + EDIT_CATEGORY_BY_ID + " for edit category by id");
        System.out.println("please input " + DELETE_CATEGORY_BY_ID + " for delete category by id");
        System.out.println("please input " + ADD_PRODUCT + " for add product");
        System.out.println("please input " + EDIT_PRODUCT_BY_ID + " for edit product by id");
        System.out.println("please input " + DELETE_PRODUCT_BY_ID + " for delete product by id");
        System.out.println("please input " + PRINT_SUM_OF_PRODUCTS + " for print sum of product");
        System.out.println("please input " + PRINT_MAX_OF_PRICE_PRODUCTS + " for print max of price product");
        System.out.println("please input " + PRINT_MIN_OF_PRICE_PRODUCTS + " for print min of price product");
        System.out.println("please input " + PRINT_AVG_OF_PRICE_PRODUCTS + " for print avg of price product");
    }


}
