package com.skill3.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.skill3.entity.Product;
import com.skill3.util.HibernateUtil;

public class ProductDAO {

    // Task 2: Insert a Product
    public void insertProduct(Product product) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(product);
            tx.commit();
            System.out.println("Inserted: " + product);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Task 3a: Sort by Price Ascending
    public List<Product> getProductsSortedByPriceAsc() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(
                    "FROM Product p ORDER BY p.price ASC", Product.class);
            return query.list();
        }
    }

    // Task 3b: Sort by Price Descending
    public List<Product> getProductsSortedByPriceDesc() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(
                    "FROM Product p ORDER BY p.price DESC", Product.class);
            return query.list();
        }
    }

    // Task 4: Sort by Quantity Highest First
    public List<Product> getProductsSortedByQuantityDesc() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(
                    "FROM Product p ORDER BY p.quantity DESC", Product.class);
            return query.list();
        }
    }

    // Task 5a: Pagination - First 3 Products
    public List<Product> getFirstPage() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(
                    "FROM Product p ORDER BY p.id ASC", Product.class);
            query.setFirstResult(0);
            query.setMaxResults(3);
            return query.list();
        }
    }

    // Task 5b: Pagination - Next 3 Products
    public List<Product> getSecondPage() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(
                    "FROM Product p ORDER BY p.id ASC", Product.class);
            query.setFirstResult(3);
            query.setMaxResults(3);
            return query.list();
        }
    }

    // Task 6a: Count Total Products
    public long countAllProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(p) FROM Product p", Long.class);
            return query.uniqueResult();
        }
    }

    // Task 6b: Count Products Where quantity > 0
    public long countProductsInStock() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0", Long.class);
            return query.uniqueResult();
        }
    }

    // Task 6c: Count Products Grouped by Description
    public List<Object[]> countProductsByDescription() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Object[]> query = session.createQuery(
                    "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description",
                    Object[].class);
            return query.list();
        }
    }

    // Task 6d: Min and Max Price
    public Object[] getMinMaxPrice() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Object[]> query = session.createQuery(
                    "SELECT MIN(p.price), MAX(p.price) FROM Product p", Object[].class);
            return query.uniqueResult();
        }
    }

    // Task 7: Group By Description
    public List<Object[]> groupProductsByDescription() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Object[]> query = session.createQuery(
                    "SELECT p.description, COUNT(p), AVG(p.price) FROM Product p GROUP BY p.description",
                    Object[].class);
            return query.list();
        }
    }

    // Task 8: WHERE - Filter by Price Range
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(
                    "FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice ORDER BY p.price ASC",
                    Product.class);
            query.setParameter("minPrice", minPrice);
            query.setParameter("maxPrice", maxPrice);
            return query.list();
        }
    }

    // Task 9a: LIKE - Names Starting With a Prefix
    public List<Product> getProductsNameStartsWith(String prefix) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(
                    "FROM Product p WHERE p.name LIKE :pattern", Product.class);
            query.setParameter("pattern", prefix + "%");
            return query.list();
        }
    }

    // Task 9b: LIKE - Names Ending With a Suffix
    public List<Product> getProductsNameEndsWith(String suffix) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(
                    "FROM Product p WHERE p.name LIKE :pattern", Product.class);
            query.setParameter("pattern", "%" + suffix);
            return query.list();
        }
    }

    // Task 9c: LIKE - Names Containing a Substring
    public List<Product> getProductsNameContains(String substring) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(
                    "FROM Product p WHERE p.name LIKE :pattern", Product.class);
            query.setParameter("pattern", "%" + substring + "%");
            return query.list();
        }
    }

    // Task 9d: LIKE - Names With Exact Character Length
    public List<Product> getProductsNameWithLength(int length) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String pattern = "_".repeat(length);
            Query<Product> query = session.createQuery(
                    "FROM Product p WHERE p.name LIKE :pattern", Product.class);
            query.setParameter("pattern", pattern);
            return query.list();
        }
    }
}
