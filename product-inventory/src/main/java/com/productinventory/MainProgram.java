package com.productinventory;

import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.productinventory.dao.ProductDAO;
import com.productinventory.entity.Product;

public class MainProgram {

    public static void main(String[] args) {

        // Create Hibernate SessionFactory
        SessionFactory sessionFactory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .buildSessionFactory();

        // Create DAO object
        ProductDAO dao = new ProductDAO(sessionFactory);

        // Create Scanner object
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println();
            System.out.println("====================================");
            System.out.println("       PRODUCT INVENTORY SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.println("====================================");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                // =========================
                // ADD PRODUCT
                // =========================
                case 1:

                    scanner.nextLine();

                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();

                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();

                    Product product =
                            new Product(
                                    name,
                                    category,
                                    price,
                                    quantity
                            );

                    dao.addProduct(product);

                    break;

                // =========================
                // VIEW PRODUCTS
                // =========================
                case 2:

                    dao.viewProducts();

                    break;

                // =========================
                // UPDATE PRODUCT
                // =========================
                case 3:

                    System.out.print("Enter Product ID to update: ");
                    long updateId = scanner.nextLong();

                    scanner.nextLine();

                    System.out.print("Enter new Product Name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter new Category: ");
                    String newCategory = scanner.nextLine();

                    System.out.print("Enter new Price: ");
                    double newPrice = scanner.nextDouble();

                    System.out.print("Enter new Quantity: ");
                    int newQuantity = scanner.nextInt();

                    dao.updateProduct(
                            updateId,
                            newName,
                            newCategory,
                            newPrice,
                            newQuantity
                    );

                    break;

                // =========================
                // DELETE PRODUCT
                // =========================
                case 4:

                    System.out.print("Enter Product ID to delete: ");
                    long deleteId = scanner.nextLong();

                    dao.deleteProduct(deleteId);

                    break;

                // =========================
                // EXIT
                // =========================
                case 5:

                    System.out.println();
                    System.out.println(
                            "Thank you for using Product Inventory System."
                    );

                    scanner.close();

                    sessionFactory.close();

                    System.exit(0);

                    break;

                default:

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }
}