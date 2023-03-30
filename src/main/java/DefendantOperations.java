import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DefendantOperations {

    Scanner input = new Scanner(System.in);
    SQL_Connection newCon = new SQL_Connection();

    public void defendantMenu() {

        int preference = 0;
        do {
            System.out.println("================================================================");
            System.out.println("Welcome to the defendant operations menu" +
                    "\n1-Add defendant" +
                    "\n2-Call defendant" +
                    "\n3-Delete defendant" +
                    "\n4-List defendants" +
                    "\n0-Main menu");
            System.out.println("================================================================");
            System.out.println("Your preference...");
            preference = input.nextInt();

            switch (preference) {

                case 1:
                    addDefendant();
                    break;
                case 2:
                    callDefendant();
                    break;
                case 3:
                    delDefendant();
                    break;
                case 4:
                    listDefendants();
                    break;
                case 0:
                    System.out.println("You are being redirected to the main menu");
                    break;
                default:
                    System.out.println("Incorrect entry");
            }

        } while (preference != 0);
    }


    public void addDefendant() {
        input.nextLine();

        System.out.println("To register a new defendant ");

        System.out.println("Case number       :");
        String case_number = input.nextLine();

        System.out.println("Name        : ");
        String name = input.nextLine();

        System.out.println("Surname     : ");
        String surname = input.nextLine();

        System.out.println("Address        : ");
        String address = input.nextLine();

        String strSql = "insert into defendant values (" + "'" + case_number + "'" + ", " + "'" + name + "'" + ", " + "'" + surname + "'" + ", " + "'" + address + "'" + ")";
        Statement st = newCon.connect();

        int isAdded = 0;
        try {
            isAdded = st.executeUpdate(strSql);
        } catch (SQLException e) {
            System.out.println("SQL exception occurred: " + e.getMessage());
        }

        if (isAdded > 0) {
            System.out.println("Defendant has been successfully added.");
        } else {
            System.out.println("Defendant could not be added.");
        }
        newCon.closeConnection();
    }


    public void callDefendant() {
        input.nextLine();
        try {
            System.out.println("To call a defendant ");
            System.out.println("Name        : ");
            String name = input.nextLine();
            Statement st = newCon.connect();

            String strSql = "select * from defendant where name=" + "'" + name + "'";

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

    public void delDefendant() {
        input.nextLine();
        try {
            System.out.println("Enter the name of the defendant to be deleted");
            String name = input.nextLine();

            String strSql = "DELETE FROM defendant WHERE name=" + "'" + name + "'";
            Statement st = newCon.connect();

            int isDeleted = st.executeUpdate(strSql);

            if (isDeleted > 0) {
                System.out.println("Defendant is deleted.");
            } else {
                System.out.println("Person could not found.");
            }

            newCon.closeConnection();
        } catch (SQLException e) {
            System.out.println("SQL exception occurred: " + e.getMessage());
        }
    }

    public void listDefendants() {
        try {
            System.out.println("Please enter the case number ");
            input.nextLine();
            System.out.println("Case number     : ");
            String case_number = input.nextLine();
            Statement st = newCon.connect();

            String strSql = "select * from defendant where case_number=" + "'" + case_number + "'";

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
