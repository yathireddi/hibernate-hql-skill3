package com.skill3.main;

import java.util.List;

import com.skill3.dao.ProductDAO;
import com.skill3.entity.Product;
import com.skill3.util.HibernateUtil;

public class MainApp {

    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

        // Task 2: Insert 8 Products
        System.out.println("\n========== TASK 2: INSERTING PRODUCTS ==========");
        dao.insertProduct(new Product("Laptop",      "Electronics", 75000.00, 10));
        dao.insertProduct(new Product("Smartphone",  "Electronics", 45000.00,  5));
        dao.insertProduct(new Product("Headphones",  "Electronics",  3500.00,  0));
        dao.insertProduct(new Product("Desk Chair",  "Furniture",   12000.00,  8));
        dao.insertProduct(new Product("Monitor",     "Electronics", 18000.00,  3));
        dao.insertProduct(new Product("Notebook",    "Stationery",    150.00, 50));
        dao.insertProduct(new Product("Pen",         "Stationery",     20.00,100));
        dao.insertProduct(new Product("Bookshelf",   "Furniture",    8500.00,  2));

        // Task 3a: Sort by Price Ascending
        System.out.println("\n========== TASK 3a: SORT BY PRICE ASCENDING ==========");
        dao.getProductsSortedByPriceAsc().forEach(System.out::println);

        // Task 3b: Sort by Price Descending
        System.out.println("\n========== TASK 3b: SORT BY PRICE DESCENDING ==========");
        dao.getProductsSortedByPriceDesc().forEach(System.out::println);

        // Task 4: Sort by Quantity Highest First
        System.out.println("\n========== TASK 4: SORT BY QUANTITY (HIGHEST FIRST) ==========");
        dao.getProductsSortedByQuantityDesc().forEach(System.out::println);

        // Task 5a: Pagination - First 3
        System.out.println("\n========== TASK 5a: PAGE 1 - FIRST 3 PRODUCTS ==========");
        dao.getFirstPage().forEach(System.out::println);

        // Task 5b: Pagination - Next 3
        System.out.println("\n========== TASK 5b: PAGE 2 - NEXT 3 PRODUCTS ==========");
        dao.getSecondPage().forEach(System.out::println);

        // Task 6a: Count Total Products
        System.out.println("\n========== TASK 6a: COUNT TOTAL PRODUCTS ==========");
        System.out.println("Total Products: " + dao.countAllProducts());

        // Task 6b: Count Products in Stock
        System.out.println("\n========== TASK 6b: COUNT PRODUCTS WHERE QUANTITY > 0 ==========");
        System.out.println("Products In Stock: " + dao.countProductsInStock());

        // Task 6c: Count Grouped by Description
        System.out.println("\n========== TASK 6c: COUNT GROUPED BY DESCRIPTION ==========");
        for (Object[] row : dao.countProductsByDescription()) {
            System.out.println("  Category: " + row[0] + " | Count: " + row[1]);
        }

        // Task 6d: Min and Max Price
        System.out.println("\n========== TASK 6d: MIN AND MAX PRICE ==========");
        Object[] minMax = dao.getMinMaxPrice();
        System.out.println("  Min Price: " + minMax[0] + " | Max Price: " + minMax[1]);

        // Task 7: Group By Description
        System.out.println("\n========== TASK 7: GROUP BY DESCRIPTION ==========");
        for (Object[] row : dao.groupProductsByDescription()) {
            System.out.printf("  Category: %-15s | Count: %s | Avg Price: %.2f%n",
                    row[0], row[1], (Double) row[2]);
        }

        // Task 8: WHERE Price Range
        System.out.println("\n========== TASK 8: WHERE PRICE BETWEEN 1000 AND 50000 ==========");
        dao.getProductsByPriceRange(1000.0, 50000.0).forEach(System.out::println);

        // Task 9a: LIKE - Starts With "L"
        System.out.println("\n========== TASK 9a: LIKE - NAMES STARTING WITH 'L' ==========");
        dao.getProductsNameStartsWith("L").forEach(System.out::println);

        // Task 9b: LIKE - Ends With "k"
        System.out.println("\n========== TASK 9b: LIKE - NAMES ENDING WITH 'k' ==========");
        dao.getProductsNameEndsWith("k").forEach(System.out::println);

        // Task 9c: LIKE - Contains "one"
        System.out.println("\n========== TASK 9c: LIKE - NAMES CONTAINING 'one' ==========");
        dao.getProductsNameContains("one").forEach(System.out::println);

        // Task 9d: LIKE - Exact Length 3
        System.out.println("\n========== TASK 9d: LIKE - NAMES WITH EXACT LENGTH 3 ==========");
        dao.getProductsNameWithLength(3).forEach(System.out::println);

        HibernateUtil.shutdown();
        System.out.println("\n========== ALL TASKS COMPLETED SUCCESSFULLY ==========");
    }
}
