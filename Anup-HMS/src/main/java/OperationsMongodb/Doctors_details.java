package OperationsMongodb;

import Operation.Doctors;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Doctors_details {
    MongoDatabase mongoDatabase;
    Scanner scanner;
   Doctors doctors;
    MongoCollection<Document> collection;

    public Doctors_details(MongoDatabase mongoDatabase, Scanner scanner, Doctors doctors) {
        this.mongoDatabase=mongoDatabase;
        this.doctors=doctors;
        this.scanner=scanner;
        collection=mongoDatabase.getCollection("Doctor_details");
    }


    public void insert_datails(){
        System.out.print("How many doctors you want to insert : ");
        int option= scanner.nextInt();
        scanner.nextLine();
        if(option==1){
            System.out.println("========== Enter the details of doctors ==========");
            System.out.print("Enter the doctor id (d_id) :");
            int d_id=scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter the doctor name : ");
            String d_name= scanner.nextLine();
            System.out.print("Enter the specialization of doctor: ");
            String d_spc=scanner.nextLine();
            System.out.print("Enter the degree of doctor :");
            String d_degree=scanner.nextLine();
            System.out.print("Enter the Experience of doctor : ");
             int d_exp=scanner.nextInt();
             scanner.nextLine();

            Document d=new Document("d_id",d_id).append("name",d_name).
                    append("specialization",d_spc).append("degree",d_degree)
                    .append("Experience",d_exp).append("active",true);
            collection.insertOne(d);
            System.out.println("details inserted successfully..........");

        }else{
            List<Document> d_list= new ArrayList<>();
            for(int i=1;i<=option;i++){
                System.out.println("========== Enter the details of doctors "+i+" ==========");
                System.out.print("Enter the doctor id (d_id) :");
                int d_id=scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter the doctor name : ");
                String d_name= scanner.nextLine();
                System.out.print("Enter the specialization of doctor: ");
                String d_spc=scanner.nextLine();
                System.out.print("Enter the degree of doctor :");
                String d_degree=scanner.nextLine();
                System.out.print("Enter the Experience of doctor");
                int d_exp=scanner.nextInt();
                scanner.nextLine();
                Document d=new Document("d_id",d_id).append("name",d_name).
                        append("specialization",d_spc).append("degree",d_degree)
                        .append("experience",d_exp).append("active",true);
                d_list.add(d);
            }
            collection.insertMany(d_list);
            System.out.println("details inserted successfully..........");

        }

    }

    public void view_doctors(){
        System.out.println("Whom do you want to see the data \n1. one doctors \n2. All doctors ");
        System.out.print("Enter you choice : ");
        int choice=scanner.nextInt();
        scanner.nextLine();
        if(choice==1){
            System.out.print("Please enter the doctors id : ");
            int id=scanner.nextInt();
            scanner.nextLine();
            FindIterable<Document> document=collection.find(Filters.and(Filters.eq("d_id",id),Filters.eq("active",true)));
            Iterator<Document> iterator= document.iterator();
            System.out.println("|-------|------------------|--------------------------|---------------------------------|--------------|");
            System.out.println("| P_id  | Name             | Specialization           | Degree                          | Experience   |");
            System.out.println("|-------|------------------|--------------------------|---------------------------------|--------------|");
            while (iterator.hasNext()) {
                Document doc = iterator.next();
                Integer d_idInteger = doc.getInteger("d_id");
                Integer experienceInteger = doc.getInteger("experience");

                // Check if "d_id" and "experience" fields are not null
                if (d_idInteger != null && experienceInteger != null) {
                    int d_id = d_idInteger.intValue();
                    String d_name = doc.getString("name");
                    String d_spc = doc.getString("specialization");
                    String d_degree = doc.getString("degree");
                    int experience = experienceInteger.intValue();

                    System.out.printf("|%-7s|%-18s|%-26s|%-34s|%-13s| \n", d_id, d_name, d_spc, d_degree, experience);
                }
            }
            System.out.println("|-------|------------------|--------------------------|---------------------------------|--------------|");




        }else if(choice==2){
            FindIterable<Document> document=collection.find(Filters.eq("active",true));
            Iterator<Document> iterator= document.iterator();
            System.out.println("|-------|------------------|--------------------------|---------------------------------|--------------|");
            System.out.println("| P_id  | Name             | Specialization           | Degree                          | Experience   |");
            System.out.println("|-------|------------------|--------------------------|---------------------------------|--------------|");
            while(iterator.hasNext()){
                Document doc = iterator.next();
                int d_id=doc.getInteger("d_id");
                String d_name=doc.getString("name");
                String d_spc=doc.getString("specialization");
                String d_degree=doc.getString("degree");
                int experience=doc.getInteger("experience");
                System.out.printf("|%-7s|%-18s|%-26s|%-34s|%-13s| \n",d_id,d_name,d_spc,d_degree,experience);

            }
            System.out.println("|-------|------------------|--------------------------|---------------------------------|--------------|");

        }else{
            System.out.println("please enter the valid input");
        }

    }


    public void update_doctors(){
        System.out.print("Please Enter the id of doctor to update : ");
        int d_id=scanner.nextInt();
        scanner.nextLine();
        System.out.println("what dou you want to update \n1. Specialization\n2. Experience");
        int option=scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                System.out.print("Please Enter the Updated specialization :");
                String d_spc=scanner.nextLine();
                collection.updateOne(Filters.eq("d_id",d_id), Updates.set("specialization",d_spc));
                break;
            case 2:
                System.out.print("Please Enter the Updated Experience :");
                String d_exp=scanner.nextLine();
                collection.updateOne(Filters.eq("d_id",d_id), Updates.set("experience",d_exp));
                break;

            default:
                System.out.println("Please choose the valid options");

        }

        System.out.println("Details are successfully updated....!");
    }

    public void delete_doctors(){
        System.out.print("please Enter the id of doctor : ");
        int id=scanner.nextInt();
        scanner.nextLine();

        collection.updateOne(Filters.eq("d_id",id), Updates.set("active",false));
        System.out.println("doctors_details are deleted successfully");
    }




}
