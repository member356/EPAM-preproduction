package com.epam.khrypushyna.shop.dao;

import com.epam.khrypushyna.shop.db.JDBCConnectionHolder;
import com.epam.khrypushyna.shop.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class);

    private static final String QUERY_INSERT_USER = "INSERT INTO users VALUES (DEFAULT,?,?,?,?,?)";
    private static final String QUERY_SELECT_USERS = "SELECT * FROM users";
    private static final String QUERY_SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

    @Override
    public void addUser(User user) {
        Connection conn;
        PreparedStatement pst;
        try {
            conn = JDBCConnectionHolder.getConnection();
            pst = conn.prepareStatement(QUERY_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getFirstName());
            pst.setString(5, user.getLastName());
            pst.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            LOG.error("Exception while inserting user. " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection conn;
        Statement st;
        ResultSet rs;
        User user;
        try {
            conn = JDBCConnectionHolder.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(QUERY_SELECT_USERS);
            while (rs.next()) {
                user = createUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            LOG.error("Exception while get all users. " + e.getMessage());
        }
        return users;
    }

    @Override
    public User getUser(String login) {
        User user = null;
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        try {
            conn = JDBCConnectionHolder.getConnection();
            pst = conn.prepareStatement(QUERY_SELECT_USER_BY_LOGIN);
            pst.setString(1, login);
            rs = pst.executeQuery();
            if (rs.next()) {
                user = createUser(rs);
            }
        } catch (SQLException e) {
            LOG.error("Exception in getting connection or preparing the statement" + e.getMessage());
        }
        return user;
    }

    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("firstname"));
        user.setLastName(rs.getString("lastname"));
        return user;
    }

}
