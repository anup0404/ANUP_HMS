package Connection;

import com.mongodb.MongoException;
import com.mongodb.client.*;

import java.util.Scanner;

public class MongodbConnection {
    Scanner scanner;
    public MongodbConnection(Scanner scanner) {
        this.scanner=scanner;
    }

    public  MongoDatabase getDatabase() {
        MongoDatabase database=null;
        System.out.print("Enter your mongodb connection url (mongodb://localhost:27017) : ");
        String url = scanner.nextLine();
        try{
            MongoClient mongoClient = MongoClients.create(url);
            database = mongoClient.getDatabase("Hospital");

        } catch (MongoException e) {
            System.err.println("An error occurred while connecting to MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
        return database;
    }
}
