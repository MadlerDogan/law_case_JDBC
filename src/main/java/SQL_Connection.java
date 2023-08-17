import java.sql.*;

public class SQL_Connection {

    private static Connection con;
    private static Statement st;
    private static ResultSet rsSet;

    public Statement connect(){
        try {
        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/law_case",
                "postgres",
                "123");

        Statement st = con.createStatement();
        return st;
           } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL exception occurred: " + e.getMessage());
        }
        return null;
    }


    public void closeConnection() {
        try {
            if (rsSet != null) {
                rsSet.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






}
