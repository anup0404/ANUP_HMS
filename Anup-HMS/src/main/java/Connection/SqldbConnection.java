package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class SqldbConnection {

//    private static final String url="jdbc:mysql://localhost:3306/hospital";
//
//    private static final String username="root";
//
//    private static final String password="Gupta#0404";
 static  Scanner  scanner;


    public SqldbConnection(Scanner scanner) {
        this.scanner=scanner;
    }

    public static Connection getConnection(){
        Connection connection=null;

         System.out.print("Enter your sql database url (jdbc:mysql://localhost:3306/hospital) : ");
         String url = scanner.nextLine();
         System.out.print("Enter your  sql username (root) :");
         String username = scanner.nextLine();
         System.out.print("Enter your sql password (Gupta#0404) :");
         String password = scanner.nextLine();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
           connection= DriverManager.getConnection(url,username,password);

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }

  return connection;
    }
}
