package com.gepardec.training.microprofile.advanced.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Dependent
public class PropertyDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String URL = "jdbc:postgresql://localhost:15432/mptraining";
    public static final String USER = "admin";
    public static final String PASSWORD = "admin@123";

    public Set<String> findPropertyNames() {
        Set<String> propertyNames = new HashSet<>();
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT p.key FROM property p ORDER BY p.key")) {
            while (resultSet.next()) {
                propertyNames.add(resultSet.getString("KEY"));
            }
        } catch (SQLException throwables) {
            logger.error("Unable to execute query to find property names.", throwables);
        }
        return propertyNames;
    }

    public String findProperty(String key) {

        try (Connection connection = createConnection();
             PreparedStatement statement = createStatement(key, connection);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getString("VALUE");
            }
            return null;

        } catch (SQLException throwables) {
            logger.error("Unable to execute Query to find property for key " + key, throwables);
        }
        return null;
    }

    private Connection createConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Unable to find class org.postgresql.Driver");
        }
        Properties connectionProps = new Properties();
        connectionProps.put("user", USER);
        connectionProps.put("password", PASSWORD);
        return DriverManager.getConnection(URL, connectionProps);
    }

    private static PreparedStatement createStatement(String key, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT p.value FROM property p WHERE KEY = ?");
        statement.setString(1, key);
        return statement;
    }
}
