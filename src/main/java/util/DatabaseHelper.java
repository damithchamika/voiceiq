package util;


import java.sql.*;
import java.util.logging.Logger;

public class DatabaseHelper {
    private static final Logger LOG = Logger.getLogger(DatabaseHelper.class.getName());
    private String dbPrefix = "jdbc:postgresql://";
    private Connection con = null;
    private ResultSet resultSet = null;
    Statement statement;
    String dbHost = "jdbc:postgresql://172.15.100.131";
    String dbUser = "Admin";
    String dbPassWord = "admin123";

    public void setConnection(String databaseName) {
        try {
            con = DriverManager.getConnection(dbHost, dbUser, dbPassWord);
        } catch (SQLException e) {
            LOG.warning("Error occurred while connecting to Database " + e);
        }
    }

    public ResultSet runQuery(String query) {
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            LOG.info("Error executing query: " + e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOG.info("Error closing the connection" + e);
                }
            }

        }
        return resultSet;
    }

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            LOG.info("Error closing the connection : " + e);
        }
    }

}

