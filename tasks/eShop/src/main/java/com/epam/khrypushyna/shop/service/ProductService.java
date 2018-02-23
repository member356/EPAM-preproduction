package com.epam.khrypushyna.shop.service;

import com.epam.khrypushyna.shop.entity.Builder;
import com.epam.khrypushyna.shop.entity.Category;
import com.epam.khrypushyna.shop.entity.Product;

import java.util.List;

public interface ProductService {

    void add(Product product);

    List<Product> getAllProduct();

    Product getProductById(int id);

    List<Product> getProductsByCategory(Category category);

    List<Product> getProductsByBuilder(Builder builder);

    List<Product> getProductsByPrice(int from, int to);

    List<Product> getProductsByArea(int from, int to);
}
