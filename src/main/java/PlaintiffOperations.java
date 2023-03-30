import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PlaintiffOperations {

    Scanner input = new Scanner(System.in);
    SQL_Connection newCon = new SQL_Connection();

    public void plaintiffMenu() throws SQLException, ClassNotFoundException {

        int preference = 0;
        do {
            System.out.println("================================================================");
            System.out.println("Welcome to the plaintiff operations menu" +
                    "\n1-Add plaintiff" +
                    "\n2-Call plintiff" +
                    "\n3-Delete plaintiff " +
                    "\n4-List plaintiffs" +
                    "\n0-Main menu");
            System.out.println("================================================================");
            System.out.println("Your preference...");
            preference = input.nextInt();

            switch (preference) {

                case 1:
                    addPlaintiff();
                    break;
                case 2:
                    callPlaintiff();
                    break;
                case 3:
                    deletePlaintiff();
                    break;
                case 4:
                    listPlaintiff();
                    break;
                case 0:
                    System.out.println("You are being redirected to the main menu.");
                    break;
                default:
                    System.out.println("Incorrect entry");
            }

        } while (preference != 0);

    }


    public void addPlaintiff() {
        input.nextLine();

        System.out.println("To register a new plaintiff... ");

        System.out.println("Case number       :");
        String case_number = input.nextLine();

        System.out.println("Name        : ");
        String name = input.nextLine();

        System.out.println("Surname     : ");
        String surname = input.nextLine();

        System.out.println("Address        : ");
        String address = input.nextLine();


        String strSql = "insert into plaintiff values (" + "'" + case_number + "'" + ", " + "'" + name + "'" + ", " + "'" + surname + "'" + ", " + "'" + address + "'" + ")";

        Statement st = newCon.connect();
        int isAdded = 0;
        try {
            isAdded = st.executeUpdate(strSql);
        } catch (SQLException e) {
            System.out.println("SQL exception occurred: " + e.getMessage());
            ;
        }

        if (isAdded > 0) {
            System.out.println("Plaintiff has been successfully added.");
        } else {
            System.out.println("Plaintiff could not be added.");
        }

        newCon.closeConnection();
    }


    public void callPlaintiff() {
        input.nextLine();
        try {
            System.out.println("To call a plaintiff");
            System.out.println("Name        : ");
            String name = input.nextLine();

            Statement st = newCon.connect();

            String strSql = "select * from plaintiff where name=" + "'" + name + "'";

            ResultSet data = st.executeQuery(strSql);

            while (data.next()) {

                System.out.println(data.getString("case_number") +
                        "  " + data.getString("name") +
                        "  " + data.getString("surname") +
                        "  " + data.getString("address"));
            }


            newCon.closeConnection();
        } catch (SQLException e) {
            System.out.println("SQL exception occurred: " + e.getMessage());
        }

    }
        public void deletePlaintiff () {
            input.nextLine();

            try {
                System.out.println("Enter the name of the plaintiff to be deleted");
                String name = input.nextLine();

                Statement st = newCon.connect();
                String strSql = "DELETE FROM plaintiff WHERE name=" + "'" + name + "'";

                int isDeleted = st.executeUpdate(strSql);
                if (isDeleted > 0) {
                    System.out.println("Plaintiff is deleted.");
                } else {
                    System.out.println("Person not found.");
                }
                newCon.closeConnection();
            } catch (SQLException e) {
                System.out.println("SQL exception occurred: " + e.getMessage());
            }
        }


    public void listPlaintiff() {

        try {
            System.out.println("Please enter the case number ");
            input.nextLine();
            System.out.println("Case number     : ");
            String case_number = input.nextLine();

            Statement st = newCon.connect();

            String strSql = "select * from plaintiff where case_number=" + "'" + case_number + "'";

            ResultSet data = st.executeQuery(strSql);

            while (data.next()) {

                System.out.println(data.getString("case_number") +
                        "  " + data.getString("name") +
                        "  " + data.getString("surname") +
                        "  " + data.getString("address"));
            }

            newCon.closeConnection();
        } catch (SQLException e) {
            System.out.println("SQL exception occurred: " + e.getMessage());
        }
    }
}
