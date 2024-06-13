package org.example;

import Connection.MongodbConnection;
import Connection.SqldbConnection;
import Operation.Appointment;
import Operation.Doctors;
import Operation.Patients;
import OperationsMongodb.Doctors_details;
import OperationsMongodb.Patients_details;
import Transactions.Doctors_Transaction;
import Transactions.Patients_Transaction;
import com.mongodb.client.MongoDatabase;
import java.sql.Connection;
import java.util.Scanner;

public  class Main{
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        MongodbConnection mongo=new MongodbConnection(scanner);
        SqldbConnection sql=new SqldbConnection(scanner);

        // three  things i have to pass to other;

        Connection connection=sql.getConnection();
        MongoDatabase mongoDatabase=mongo.getDatabase();


        Patients patients=new Patients(connection,scanner);
        Doctors doctors=new Doctors(connection);
        Patients_Transaction pt=new Patients_Transaction(connection,scanner,patients,doctors);
        Doctors_Transaction dt=new Doctors_Transaction(connection,scanner,patients,doctors);
        Appointment appointment=new Appointment(connection,scanner,patients,doctors,pt,dt);

        Patients_details pd=new Patients_details(mongoDatabase,scanner,patients);
        Doctors_details dd=new Doctors_details(mongoDatabase,scanner,doctors);
        Permission permission=new Permission(pd,dd,appointment,patients,doctors,scanner);

  while(true) {
      System.out.println("Who are you ? \n1. Admin\n2. Employee\n3. Return");
      int choice = scanner.nextInt();
      scanner.nextLine();
      if (choice == 2) {
          permission.worker_option();
      } else if (choice == 1) {
          permission.admin_option();
      } else if (choice==3) {return;

      } else {
          System.out.println("Please select the valid choice : ");
      }

  }

    }
}