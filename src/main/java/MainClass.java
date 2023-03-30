import java.sql.SQLException;
import java.util.Scanner;

public class MainClass {

    Scanner input = new Scanner(System.in);
    PlaintiffOperations plaintiffOperations = new PlaintiffOperations();
    DefendantOperations defendantOperations = new DefendantOperations();
    SQL_Connection sqlConnection = new SQL_Connection();
    public void caseMenu() throws SQLException, ClassNotFoundException {


        String preference="";
        do {
            System.out.println("================================================================");
            System.out.println("Welcome to the case menu" +
                    "\n1-Plaintiff Menu" +
                    "\n2-Defendant Menu" +
                    "\n0-Exit ");

            System.out.println("================================================================");
            System.out.println("Tercihiniz...");
            preference = input.next();

            switch (preference) {

                case "1":
                    plaintiffOperations.plaintiffMenu();
                    break;
                case "2":
                    defendantOperations.defendantMenu();
                    break;
                case "0":
                    System.out.println("You ar logging out of the system");
                    break;
                default:
                    System.out.println("Incorrect entry");
            }
        }while (!preference.equals("0"));


    }




}
