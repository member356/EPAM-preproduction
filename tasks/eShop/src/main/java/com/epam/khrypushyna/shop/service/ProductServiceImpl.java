package com.epam.khrypushyna.shop.service;

import com.epam.khrypushyna.shop.dao.ProductDAO;
import com.epam.khrypushyna.shop.db.TransactionManager;
import com.epam.khrypushyna.shop.entity.Builder;
import com.epam.khrypushyna.shop.entity.Category;
import com.epam.khrypushyna.shop.entity.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;
    private TransactionManager transactionManager;

    public ProductServiceImpl(ProductDAO productDAO, TransactionManager transactionManager) {
        this.productDAO = productDAO;
        this.transactionManager = transactionManager;
    }

    @Override
    public void add(Product product) {
        transactionManager.doTransaction(() -> {
            productDAO.add(product);
            return null;
        });
    }

    @Override
    public List<Product> getAllProduct() {
        return (List<Product>) transactionManager.doTransaction(() -> productDAO.getAllProducts());
    }

    @Override
    public Product getProductById(int id) {
        return (Product) transactionManager.doTransaction(() -> productDAO.getProductById(id));
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return (List<Product>) transactionManager.doTransaction(() -> productDAO.getProductsByCategory(category));
    }

    @Override
    public List<Product> getProductsByBuilder(Builder builder) {
        return (List<Product>) transactionManager.doTransaction(() -> productDAO.getProductsByBuilder(builder));
    }

    @Override
    public List<Product> getProductsByPrice(int from, int to) {
        return (List<Product>) transactionManager.doTransaction(() -> productDAO.getProductsByPrice(from, to));
    }

    @Override
    public List<Product> getProductsByArea(int from, int to) {
        return (List<Product>) transactionManager.doTransaction(() -> productDAO.getProductsByArea(from, to));
    }
}
