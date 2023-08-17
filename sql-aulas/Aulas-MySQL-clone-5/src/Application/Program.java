package Application;

import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            // Se manter isso aqui ele gera um erro
//            int x = 1;
//            if (x < 2) {
//                throw new SQLException("Fake error");
//            }

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();

            System.out.println(rows1 + " linhas afetadas");
            System.out.println(rows2 + " linhas afetadas");

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transação retornada! Causada por: " + e.getMessage());
            } catch (SQLException ex) {
                throw new DbException("Erro causado ao tentar reverter a transação! O problema: " + ex.getMessage());
            }
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
