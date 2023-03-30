import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    static SQL_Connection newCon = new SQL_Connection();

    public static void main(String[] args) {

        try {
            Statement st = newCon.connect();
//            String sql01 = "Create Table plaintiff (case_number varchar(10), name varchar(20), surname varchar(20), address varchar (50))";
//            st.execute(sql01);
            String sql02 = "Create Table defendant (case_number varchar(10), name varchar(20), surname varchar(20), address varchar (50))";
            st.execute(sql02);

        } catch (SQLException e) {
            System.out.println("SQL exception occurred: " + e.getMessage());
        }
    }
}
