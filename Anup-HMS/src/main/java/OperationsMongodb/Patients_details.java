package OperationsMongodb;

import Operation.Patients;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



public class Patients_details {
    MongoDatabase mongoDatabase;
    Scanner scanner;
    Patients patients;
    MongoCollection<Document> collection;

    public Patients_details(MongoDatabase mongoDatabase, Scanner scanner, Patients patients) {
        this.mongoDatabase=mongoDatabase;
        this.patients=patients;
        this.scanner=scanner;
        collection=mongoDatabase.getCollection("Patients_details");
    }


    public void insert_data(){
        System.out.print("How many patients you want to insert : ");
        int option= scanner.nextInt();
        scanner.nextLine();
        if(option==1){
            System.out.println("========== Enter the details of patients ==========");
            System.out.print("Enter the patient id (p_id) :");
            int p_id=scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter the patient name : ");
            String p_name= scanner.nextLine();
            System.out.print("Enter the decease of patients : ");
            String p_decease=scanner.nextLine();
            System.out.print("Enter the medicine of patients :");
            String p_medicine=scanner.nextLine();
            System.out.print("Enter the blood group of patients");
            String p_blood_group=scanner.nextLine();
            Document p=new Document("p_id",p_id).append("name",p_name).
                    append("desease",p_decease).append("medicine",p_medicine)
                    .append("blood_group",p_blood_group).append("active",true);
            collection.insertOne(p);
            System.out.println("details inserted successfully..........");

        }else{
            List<Document> p_list= new ArrayList<>();
            for(int i=1;i<=option;i++){
                System.out.println("========== Enter the details of patients "+i+" ==========");
                System.out.print("Enter the patient id (p_id) :");
                int p_id=scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter the patient name : ");
                String p_name= scanner.nextLine();
                System.out.print("Enter the decease of patients : ");
                String p_decease=scanner.nextLine();
                System.out.print("Enter the medicine of patients :");
                String p_medicine=scanner.nextLine();
                System.out.print("Enter the blood group of patients :");
                String p_blood_group=scanner.nextLine();
                Document p=new Document("p_id",p_id).append("name",p_name).
                        append("desease",p_decease).append("medicine",p_medicine)
                        .append("blood_group",p_blood_group);
                p_list.add(p);
            }
            collection.insertMany(p_list);
            System.out.println("details inserted successfully..........");

        }

    }

    public void view_patients(){
        System.out.println("Whom do you want to see the data \n 1. one patients \n 2.All patients ");
        System.out.print("Enter you choice : ");
        int choice=scanner.nextInt();
        scanner.nextLine();
        if(choice==1){
            System.out.print("Please enter the patients id : ");
            int id=scanner.nextInt();
            scanner.nextLine();
            FindIterable<Document> document=collection.find(Filters.and(Filters.eq("p_id",id),Filters.eq("active",true)));
            Iterator<Document> iterator= document.iterator();
            System.out.println("|-------|------------------|--------------------------|---------------------------------------------|--------------|");
            System.out.println("| P_id  | Name             | Decease                  | Medicine                                    | Blood Group  |");
            System.out.println("|-------|------------------|--------------------------|---------------------------------------------|--------------|");
            while(iterator.hasNext()){
                Document d = iterator.next();
                int p_id=d.getInteger("p_id");
                String name=d.getString("name");
                String desease=d.getString("desease");
                String medicine=d.getString("medicine");
                String blood_group=d.getString("blood_group");
                System.out.printf("|%-7s|%-18s|%-26s|%-45s|%-14s| \n",p_id,name,desease,medicine,blood_group);

            }
            System.out.println("|-------|------------------|--------------------------|---------------------------------------------|--------------|");




        }else if(choice==2){
            FindIterable<Document> document=collection.find(Filters.eq("active",true));
            Iterator<Document> iterator= document.iterator();
            System.out.println("|-------|------------------|--------------------------|---------------------------------------------|--------------|");
            System.out.println("| P_id  | Name             | Decease                  | Medicine                                    | Blood Group  |");
            System.out.println("|-------|------------------|--------------------------|---------------------------------------------|--------------|");
            while(iterator.hasNext()){
                Document d = iterator.next();
                int p_id=d.getInteger("p_id");
                String name=d.getString("name");
                String desease=d.getString("desease");
                String medicine=d.getString("medicine");
                String blood_group=d.getString("blood_group");
                System.out.printf("|%-7s|%-18s|%-26s|%-45s|%-14s| \n",p_id,name,desease,medicine,blood_group);

            }
            System.out.println("|-------|------------------|--------------------------|---------------------------------------------|--------------|");



        }else{
            System.out.println("please enter the valid input");
        }

    }


    public void update_details(){
        System.out.print("Please Enter the id of patients to update : ");
        int p_id=scanner.nextInt();
        scanner.nextLine();
        System.out.println("what dou you want to update \n1. Decease\n2. Medicine\n3. Blood Group");
        int option=scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                System.out.print("Please Enter the Updated Decease :");
                String decease=scanner.nextLine();
                collection.updateOne(Filters.eq("p_id",p_id), Updates.set("desease",decease));
                break;
            case 2:
                System.out.print("Please Enter the Updated Medicine :");
                String medicine=scanner.nextLine();
                collection.updateOne(Filters.eq("p_id",p_id), Updates.set("medicine",medicine));
                break;
            case 3:
                System.out.print("Please Enter the Updated Blood Group :");
                String blood_group=scanner.nextLine();
                collection.updateOne(Filters.eq("p_id",p_id), Updates.set("blood_group",blood_group));
                break;
            default:
                System.out.println("Please choose the valid options");

        }

        System.out.println("Details are successfully updated....!");
    }

public void delete_patients(){
    System.out.print("please Enter the id of patient : ");
    int id=scanner.nextInt();
    scanner.nextLine();

    collection.updateOne(Filters.eq("p_id",id), Updates.set("active",false));
    System.out.println("patients_details are deleted successfully");
}




}
