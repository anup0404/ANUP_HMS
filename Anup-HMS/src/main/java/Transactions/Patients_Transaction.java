package Transactions;

import Operation.Doctors;
import Operation.Patients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patients_Transaction {

    Connection connection;
    Scanner scanner;
    Patients patients;
    Doctors doctors;

    public Patients_Transaction(Connection connection, Scanner scanner, Patients patients, Doctors doctors) {
        this.connection = connection;
        this.doctors = doctors;
        this.patients = patients;
        this.scanner = scanner;

    }

    public double get_balance(int patient_id) {
        try {
            String query = "select wallet from patients where id =?";
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setInt(1,patient_id);
            ResultSet resultSet= psmt.executeQuery();

            if (resultSet.next()) {
                double balance = resultSet.getDouble("wallet");
                return balance;
            } else {

                throw new RuntimeException("No balance found for patient ID: " + patient_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void update_patient_wallet(Double new_balance,Double old_balance,int patient_id,double fee){
        try{
            String query="UPDATE patients SET wallet=? where id=?";
            PreparedStatement psmt=connection.prepareStatement(query);

            psmt.setDouble(1,new_balance);
            psmt.setInt(2,patient_id);
            int affectedRow=psmt.executeUpdate();
            if(affectedRow>0){
                System.out.println(fee +" debited  from patients successfully !");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}