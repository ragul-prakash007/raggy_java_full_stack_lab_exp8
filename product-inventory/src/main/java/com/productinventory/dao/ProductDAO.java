package com.productinventory.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.productinventory.entity.Product;

public class ProductDAO {

    private SessionFactory sessionFactory;

    // Constructor
    public ProductDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // =========================
    // ADD PRODUCT
    // =========================
    public void addProduct(Product product) {

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            session.persist(product);

            transaction.commit();

            System.out.println("Product added successfully.");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    // =========================
    // VIEW PRODUCTS
    // =========================
    public void viewProducts() {

        try (Session session = sessionFactory.openSession()) {

            List<Product> products =
                    session.createQuery(
                            "FROM Product",
                            Product.class
                    ).getResultList();

            if (products.isEmpty()) {

                System.out.println();
                System.out.println("No products found.");

                return;
            }

            System.out.println();
            System.out.println("==============================================================");

            System.out.printf(
                    "%-5s %-20s %-15s %-12s %-10s%n",
                    "ID",
                    "NAME",
                    "CATEGORY",
                    "PRICE",
                    "QUANTITY"
            );

            System.out.println("==============================================================");

            for (Product product : products) {

                System.out.printf(
                        "%-5d %-20s %-15s %-12.2f %-10d%n",
                        product.getId(),
                        product.getName(),
                        product.getCategory(),
                        product.getPrice(),
                        product.getQuantity()
                );
            }

            System.out.println("==============================================================");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================
    // UPDATE PRODUCT
    // =========================
    public void updateProduct(
            Long id,
            String name,
            String category,
            double price,
            int quantity) {

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            Product product = session.get(Product.class, id);

            if (product == null) {

                System.out.println("Product not found.");

                return;
            }

            transaction = session.beginTransaction();

            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);
            product.setQuantity(quantity);

            transaction.commit();

            System.out.println("Product updated successfully.");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    // =========================
    // DELETE PRODUCT
    // =========================
    public void deleteProduct(Long id) {

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            Product product = session.get(Product.class, id);

            if (product == null) {

                System.out.println("Product not found.");

                return;
            }

            transaction = session.beginTransaction();

            session.remove(product);

            transaction.commit();

            System.out.println("Product deleted successfully.");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}