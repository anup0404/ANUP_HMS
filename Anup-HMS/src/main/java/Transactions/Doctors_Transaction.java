package Transactions;

import Operation.Doctors;
import Operation.Patients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctors_Transaction {
    Connection connection;
    Scanner scanner;
    Patients patients;
    Doctors doctors;

    public Doctors_Transaction(Connection connection, Scanner scanner, Patients patients, Doctors doctors) {
        this.connection = connection;
        this.doctors = doctors;
        this.patients = patients;
        this.scanner = scanner;

    }

    public  double get_balance(int doctor_id) {
        try {
            String query = "select doc_wallet from doctors where id = ?";
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setInt(1,doctor_id);
            ResultSet resultSet= psmt.executeQuery();
            if (resultSet.next()) {
                double balance = resultSet.getDouble("doc_wallet");
                return balance;
            } else {

                throw new RuntimeException("No balance found for patient ID: " + doctor_id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    } public  void update_doctor_wallet(Double new_balance,Double old_balance,int doctor_id,double   fee){
        try{
            String query="UPDATE doctors SET doc_wallet=? where id=?";
            PreparedStatement psmt=connection.prepareStatement(query);
            psmt.setDouble(1,new_balance);
            psmt.setInt(2,doctor_id);
            int affectedRow=psmt.executeUpdate();
            if(affectedRow>0){
                System.out.println(fee+" creadited  to doctor accounts successfully !");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}