package com.epam.khrypushyna.shop.dao;

import com.epam.khrypushyna.shop.db.JDBCConnectionHolder;
import com.epam.khrypushyna.shop.entity.Builder;
import com.epam.khrypushyna.shop.entity.Category;
import com.epam.khrypushyna.shop.entity.Product;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private static final Logger LOG = Logger.getLogger(ProductDAOImpl.class);

    private static final String QUERY_INSERT_PRODUCT = "INSERT INTO products VALUES (DEFAULT, ?, ?, ?, ?)";
    private static final String QUERY_SELECT_PRODUCTS = "SELECT * FROM products";
    private static final String QUERY_SELECT_PRODUCT_BY_ID = "SELECT * FROM products WHERE id=?";
    private static final String QUERY_SELECT_PRODUCTS_BY_CATEGORY = "SELECT * FROM products WHERE category_id=?";
    private static final String QUERY_SELECT_PRODUCTS_BY_BUILDER = "SELECT * FROM products WHERE builder_id=?";
    private static final String QUERY_SELECT_PRODUCTS_BY_PRICE = "SELECT * FROM products WHERE price>? AND price <?";
    private static final String QUERY_SELECT_PRODUCTS_BY_AREA = "SELECT * FROM products WHERE area>? AND price <?";

    @Override
    public void add(Product product) {
        Connection conn;
        PreparedStatement pst;
        try {
            conn = JDBCConnectionHolder.getConnection();
            pst = conn.prepareStatement(QUERY_INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, product.getBuilderId());
            pst.setInt(2, product.getCategoryId());
            pst.setInt(3, product.getArea());
            pst.setInt(4, product.getPrice());
            pst.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            LOG.error("Exception while inserting product. " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Connection conn;
        Statement st;
        ResultSet rs;
        Product product;
        try {
            conn = JDBCConnectionHolder.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(QUERY_SELECT_PRODUCTS);
            while (rs.next()) {
                product = createProduct(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            LOG.error("Exception while get all products. " + e.getMessage());
        }
        return products;
    }

    @Override
    public Product getProductById(int id) {
        Product product = null;
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        try {
            conn = JDBCConnectionHolder.getConnection();
            pst = conn.prepareStatement(QUERY_SELECT_PRODUCT_BY_ID);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                product = createProduct(rs);
            }
        } catch (SQLException e) {
            LOG.error("Exception in getting connection or preparing the statement. " + e.getMessage());
        }
        return product;
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        List<Product> products = new ArrayList<>();
        Connection conn;
        PreparedStatement st;
        ResultSet rs;
        Product product;
        try {
            conn = JDBCConnectionHolder.getConnection();
            st = conn.prepareStatement(QUERY_SELECT_PRODUCTS_BY_CATEGORY);
            st.setInt(1, category.getId());
            rs = st.executeQuery();
            if (rs.next()) {
                product = createProduct(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            LOG.error("Exception in getting connection or preparing the statement", e);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByBuilder(Builder builder) {
        List<Product> products = new ArrayList<>();
        Connection conn;
        PreparedStatement st;
        ResultSet rs;
        Product product;
        try {
            conn = JDBCConnectionHolder.getConnection();
            st = conn.prepareStatement(QUERY_SELECT_PRODUCTS_BY_BUILDER);
            st.setInt(1, builder.getId());
            rs = st.executeQuery();
            if (rs.next()) {
                product = createProduct(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            LOG.error("Exception in getting connection or preparing the statement", e);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByPrice(int from, int to) {
        List<Product> products = new ArrayList<>();
        Connection conn;
        PreparedStatement st;
        ResultSet rs;
        Product product;
        try {
            conn = JDBCConnectionHolder.getConnection();
            st = conn.prepareStatement(QUERY_SELECT_PRODUCTS_BY_PRICE);
            st.setInt(1, from);
            st.setInt(1, to);
            rs = st.executeQuery();
            if (rs.next()) {
                product = createProduct(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            LOG.error("Exception in getting connection or preparing the statement", e);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByArea(int from, int to) {
        List<Product> products = new ArrayList<>();
        Connection conn;
        PreparedStatement st;
        ResultSet rs;
        Product product;
        try {
            conn = JDBCConnectionHolder.getConnection();
            st = conn.prepareStatement(QUERY_SELECT_PRODUCTS_BY_AREA);
            st.setInt(1, from);
            st.setInt(1, to);
            rs = st.executeQuery();
            if (rs.next()) {
                product = createProduct(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            LOG.error("Exception in getting connection or preparing the statement", e);
        }
        return products;
    }

    private Product createProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setCategoryId(rs.getInt("category_id"));
        product.setBuilderId(rs.getInt("builder_id"));
        product.setArea(rs.getInt("area"));
        product.setPrice(rs.getInt("price"));
        return product;
    }
}
