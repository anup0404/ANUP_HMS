package Operation;

import Validator.Validator;

import java.sql.*;
import java.util.Scanner;

public class Patients{
    private  final Connection connection;
    private final  Scanner scanner;
    public Patients(Connection connection, Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }

    // adding the details of patient into the table;

    public void add_patient(){

        System.out.println("........Enter the details of the patient........");
        System.out.print("Enter the name : ");
        String name = scanner.nextLine();
        System.out.print("Enter the age : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the gender : ");
        String gender = scanner.nextLine();
        String mob_no;
        do {
            System.out.print("Enter the Mob_no (format - 9876543210) : ");
            mob_no = scanner.nextLine();
        } while(!Validator.mob_no_Validator(mob_no));
        String gmail;
        do {
            System.out.print("Enter the gmail (format - ak123@gmail.com) : ");
            gmail = scanner.nextLine();
        } while(!Validator.email_validator(gmail));


        System.out.print("Enter the wallet amount  : ");
        Double wallet = scanner.nextDouble();

        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO patients (id, name, age, gender, mob_no, email, active, wallet) VALUES (DEFAULT, ?, ?, ?, ?, ?, 'Yes', ?)";

            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,name);
            pst.setInt(2,age);
            pst.setString(3,gender);
            pst.setString(4,mob_no);
            pst.setString(5,gmail);
            pst.setDouble(6,wallet);
            scanner.nextLine();
            int affectedRow=pst.executeUpdate();
            if(affectedRow>0){
                String Commit;
                do {
                    System.out.print("What do you want to do ( commit or rollback) : ");
                    Commit = scanner.nextLine();
                    if (Commit.equals("commit")) {
                        connection.commit();
                        System.out.println("Details of patient are successfully added....");
                        break;
                    }
                    if (Commit.equals("rollback")) {
                        System.out.println("Sorry Details are not added......Please try again...");
                        connection.rollback();
                        break;

                    }
                }while(true);


            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    // viewing the details of patients;

    public void view_patient() {
        String query = "SELECT * FROM patients where active=?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,"yes");
            ResultSet resultSet = pst.executeQuery();
            System.out.println("Details of patients : ");
            System.out.println("+------------+--------------------+--------+-------------+------------+------------------+--------+");
            System.out.println("| Patient Id | Name               | Age    | Gender      | Mobile no  | Email            | Wallet |");
            System.out.println("+------------+--------------------+--------+-------------+------------+------------------+--------+");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String mob_no=resultSet.getString("mob_no");
                String email=resultSet.getString("email");
                Double wallet=resultSet.getDouble("wallet");
                System.out.printf("|%-12d|%-20s|%-8d|%-13s|%-12s|%-18s|%-8s| \n", id, name, age, gender,mob_no,email,wallet);
            }
            System.out.println("+------------+--------------------+--------+-------------+------------+------------------+--------+");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // checking that this id patent exits in our databases or not;


    public boolean get_patient_by_id(int patient_id){
        try{
            String quary="select * from patients where id=?";
            PreparedStatement psmt=connection.prepareStatement(quary);
            psmt.setInt(1, patient_id);
            ResultSet resultSet=psmt.executeQuery();
            if(resultSet.next()) return true;
            else return false;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    // updating the details of the patients;

    public void update_patients(){
        System.out.println("----- What do you want to update ----- \n 1. Name\n 2. age \n 3. gender \n 4. mob_no \n 5. email \n 6. More than one details");
        System.out.print("choose you choice : ");
        int update_choice=scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the id of the patients to whome you want to update the data : ");
        int updatable_id=scanner.nextInt();
        scanner.nextLine();
        switch (update_choice){
            case 1:
                System.out.print("Enter you updated name : ");
                String updated_name=scanner.nextLine();
                try {
                    String quary = "UPDATE PATIENTS  SET name=? where id=?";
                    PreparedStatement psmt = connection.prepareStatement(quary);
                    psmt.setString(1,updated_name);
                    psmt.setInt(2,updatable_id);
                    int affected_row=psmt.executeUpdate();
                    if(affected_row>0){
                        System.out.println("Name are successfully updated....");
                    }else{
                        System.out.println("Sorry name are not updated !!!!  ");
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
                break ;
            case 2:
                System.out.print("Enter you updated age: ");
                int updated_age=scanner.nextInt();
                scanner.nextLine();
                try {
                    String quary = "UPDATE PATIENTS  SET age=? where id=?";
                    PreparedStatement psmt = connection.prepareStatement(quary);
                    psmt.setInt(1,updated_age);
                    psmt.setInt(2,updatable_id);
                    int affected_row=psmt.executeUpdate();
                    if(affected_row>0){
                        System.out.println("Age are successfully updated....");
                    }else{
                        System.out.println("Sorry Age are not updated !!!!  ");
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.print("Enter you updated gender : ");
                String updated_gender=scanner.nextLine();
                try {
                    String quary = "UPDATE PATIENTS  SET gender=? where id=?";
                    PreparedStatement psmt = connection.prepareStatement(quary);
                    psmt.setString(1,updated_gender);
                    psmt.setInt(2,updatable_id);
                    int affected_row=psmt.executeUpdate();
                    if(affected_row>0){
                        System.out.println("Gender are successfully updated....");
                    }else{
                        System.out.println("Sorry Gender are not updated !!!!  ");
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
                break;
            case 4:
                String updated_mob_no;
                do {
                    System.out.print("Enter you updated mob_no : ");
                    updated_mob_no = scanner.nextLine();
                }while(!Validator.mob_no_Validator(updated_mob_no));
                try {
                    String quary = "UPDATE PATIENTS  SET mob_no=? where id=?";
                    PreparedStatement psmt = connection.prepareStatement(quary);
                    psmt.setString(1,updated_mob_no);
                    psmt.setInt(2,updatable_id);
                    int affected_row=psmt.executeUpdate();
                    if(affected_row>0){
                        System.out.println("Mob_no  are successfully updated....");
                    }else{
                        System.out.println("Sorry Mob_no are not updated !!!!  ");
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
                break;
            case 5:
                String updated_email;
                do {
                    System.out.print("Enter you updated email : ");
                    updated_email = scanner.nextLine();
                }while (Validator.email_validator(updated_email)) ;
                try {
                    String quary = "UPDATE PATIENTS  SET name=? where id=?";
                    PreparedStatement psmt = connection.prepareStatement(quary);
                    psmt.setString(1, updated_email);
                    psmt.setInt(2, updatable_id);
                    int affected_row = psmt.executeUpdate();
                    if (affected_row > 0) {
                        System.out.println("Email are successfully updated....");
                    } else {
                        System.out.println("Sorry Email are not updated !!!!  ");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                System.out.println("........Enter the updated details of the patient........");
                System.out.print("Enter the updated name : ");
                String name = scanner.nextLine();
                System.out.print("Enter the updated age : ");
                int age = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter the updated gender : ");
                String gender = scanner.nextLine();
                String mob_no;
                do {
                    System.out.print("Enter the updated Mob_no (format - 9876543210) : ");
                    mob_no = scanner.nextLine();
                } while (!Validator.mob_no_Validator(mob_no));
                String gmail;
                do {
                    System.out.print("Enter the updated  gmail (format - ak123@gmail.com) : ");
                    gmail = scanner.nextLine();
                } while (!Validator.email_validator(gmail));
                System.out.print(" Is he /she active ?  (yes/no) : ");
                String active = scanner.nextLine();
                try {
                    String quary = "UPDATE  patients SET name=?,age=?,gender=?,mob_no=?,email=?,active=? where id=?";
                    PreparedStatement pst = connection.prepareStatement(quary);
                    pst.setString(1, name);
                    pst.setInt(2, age);
                    pst.setString(3, gender);
                    pst.setString(4, mob_no);
                    pst.setString(5, gmail);
                    pst.setString(6, active);
                    pst.setInt(7, updatable_id);
                    int affectedRow = pst.executeUpdate();
                    if (affectedRow > 0) {
                        System.out.println("Details of patient are successfully updated....");
                    } else {
                        System.out.println("Sorry Details are not updated......Please try again...");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;

            default:
                System.out.println("Please choose the right choice......");
        }
    }


}
