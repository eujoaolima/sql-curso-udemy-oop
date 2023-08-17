package Application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            /* st = conn.prepareStatement(
                "INSERT INTO seller " +
                "(Name, Email, BirthDate, BaseSalary, DepartmentId)" +
                "VALUES " +
                "(? ,? ,?, ?, ?)" ,// Placeholder
                Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, "Julie Purple");
            st.setString(2, "julie.purple@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1995").getTime()));
            st.setDouble(4, 1000.0);
            st.setInt(5, 1); */

            st = conn.prepareStatement(
            "INSERT INTO department (Name) VALUES ('D1'), ('D2')",
                Statement.RETURN_GENERATED_KEYS
            );

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("ID: " + id);
                }
            } else {
               System.out.println("Nenhuma linha foi alterada");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
