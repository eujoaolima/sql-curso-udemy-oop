package Application;


import db.DB;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
            "DELETE FROM department "
                + "WHERE "
                + "(Id = ?)"
            );

            st.setInt(1, 5);
            st.setInt(1, 6);

            int rowsAffected = st.executeUpdate();

            System.out.println(rowsAffected + " linhas afetadas");
        } catch (DbIntegrityException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
