package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conn = null;
    public static Connection getConnection() {
        try {
            if (conn == null) {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

   private static Properties loadProperties() {
       try {
           InputStream is = DB.class.getClassLoader().getResourceAsStream("db.properties");
           Properties props = new Properties();
           props.load(is);
           return props;
       } catch (IOException e) {
           throw new DbException(e.getMessage());
       }
   }

   public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
   }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}


