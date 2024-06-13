package Operation;

import Transactions.Doctors_Transaction;
import Transactions.Patients_Transaction;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class Appointment{
    Connection connection;
    Scanner scanner;
    Patients patients;
    Doctors doctors;
    Patients_Transaction pt;
    Doctors_Transaction dt;
    public Appointment(Connection connection, Scanner scanner, Patients patients, Doctors doctors, Patients_Transaction pt, Doctors_Transaction dt){
        this.connection=connection;
        this.scanner=scanner;
        this.patients=patients;
        this.doctors=doctors;
        this.pt=pt;
        this.dt=dt;
    }

    public void book_appointment(){
        System.out.print("Enter the patient Id : ");
        int patient_id=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the Doctor Id : ");
        int doctor_id=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the appointment date (YYYY-MM-DD) : ");
        String appointment_date= scanner.nextLine();
        System.out.print("Enter the appointment time (HH:MM:SS) (24 HOUR FORMAT) : ");
        String appointment_time= scanner.nextLine();
        if(patients.get_patient_by_id(patient_id) && doctors.get_doctor_by_id(doctor_id)){

            if(check_doctor_availibility(doctor_id,appointment_date)){

                try{
                    connection.setAutoCommit(false);
                    String appointment_quary="Insert into appointments(patient_id,doctor_id,appointment_date,appointment_time)values(?,?,?,?)";
                    PreparedStatement psmt =connection.prepareStatement(appointment_quary);
                    psmt.setInt(1,patient_id);
                    psmt.setInt(2,doctor_id);
                    psmt.setString(3,appointment_date);
                    psmt.setString(4,appointment_time);
                    int affectedRow=psmt.executeUpdate();

                    if(affectedRow>0) {
                        System.out.println("Please enter the fee of doctor : ");
                        Double fee = scanner.nextDouble();
                        scanner.nextLine();
                        Double old_patient_balance = pt.get_balance(patient_id);
                        Double old_doctor_balance = dt.get_balance(doctor_id);
                        Double new_patients_balance = old_patient_balance - fee;
                        Double new_doctor_balance = old_doctor_balance + fee;
                        if (new_patients_balance >= 0) {
                            pt.update_patient_wallet(new_patients_balance, old_patient_balance,patient_id,fee);
                            dt.update_doctor_wallet(new_doctor_balance, old_doctor_balance,doctor_id,fee);
                            connection.commit();
                            System.out.println("Appointment booked successfully.....");
                        } else {
                            System.out.println("sorry ! efficient balance");
                            System.out.println("Failed to book appointment......");
                            connection.rollback();
                        }


                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }else{
            System.out.println("Either doctor or patient does not exit in this hospital..");
        }
    }
    public boolean check_doctor_availibility(int doctor_id , String appointment_date){
        try{
            String quary="select COUNT(*) from appointments where doctor_id=? and appointment_date=?";
            PreparedStatement psmt=connection.prepareStatement(quary);
            psmt.setInt(1,doctor_id);
            psmt.setString(2,appointment_date);
            ResultSet resultSet= psmt.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 0;
            }

        }catch(SQLException E){
            E.printStackTrace();
        }

        return false;
    }
    public void update_appointment(){
        System.out.print("Enter the  patient Id  to whom you want to update : ");
        int patient_id=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the updated Doctor Id : ");
        int doctor_id=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the updated appointment date (YYYY-MM-DD) : ");
        String appointment_date= scanner.nextLine();
        System.out.print("Enter the updated appointment time (HH:MM:SS) (24 HOUR FORMAT) : ");
        String appointment_time= scanner.nextLine();
        if(patients.get_patient_by_id(patient_id) && doctors.get_doctor_by_id(doctor_id)){

            if(check_doctor_availibility(doctor_id,appointment_date)){

                try{
                    connection.setAutoCommit(false);
                    String appointment_quary="update appointments set doctor_id=?,appointment_date=?,appointment_time=? where patient_id=?";
                    PreparedStatement psmt =connection.prepareStatement(appointment_quary);
                    psmt.setInt(1,doctor_id);
                    psmt.setString(2,appointment_date);
                    psmt.setString(3,appointment_time);
                    psmt.setInt(4,patient_id);
                    int affectedRow=psmt.executeUpdate();
                    if(affectedRow>0){
                        System.out.print("Please enter the fee of doctor : ");
                        Double fee=scanner.nextDouble();
                        scanner.nextLine();
                        Double old_patient_balance= pt.get_balance(patient_id);
                        Double old_doctor_balance= dt.get_balance(doctor_id);
                        Double new_patients_balance=old_patient_balance-fee;
                        Double new_doctor_balance=old_doctor_balance+new_patients_balance;
                        if(new_patients_balance>=0){
                            pt.update_patient_wallet(new_patients_balance,old_patient_balance,patient_id,fee);
                            dt.update_doctor_wallet(new_doctor_balance,old_doctor_balance,doctor_id,fee);
                            connection.commit();
                            System.out.println("Appointment updated  successfully.....");
                        }else {
                            System.out.println("sorry ! efficient balance");
                            connection.rollback();
                            System.out.println("Failed to update appointment......");
                        }


                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }else{
            System.out.println("Either doctor or patient does not exit in this hospital..");
        }
    }
    public void view_appointments(){
        String quary="select * from appointments";
        try{
            PreparedStatement psmt= connection.prepareStatement(quary);
            ResultSet resultSet= psmt.executeQuery();
            System.out.println("Details of appointments are......");
            System.out.println("+----+------------+-----------+------------------+------------------+");
            System.out.println("| id | patient_id | doctor_id | appointment_date | appointment_time |");
            System.out.println("+----+------------+-----------+------------------+------------------+");
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                int patient_id=resultSet.getInt("patient_id");
                int doctor_id=resultSet.getInt("doctor_id");
                Date appointment_date=resultSet.getDate("appointment_date");
                Time appointment_time=resultSet.getTime("appointment_time");
                System.out.printf("|%-4s|%-12s|%-11s|%-18s|%-18s| \n",id,patient_id,doctor_id,appointment_date,appointment_time);

            }
            System.out.println("+----+------------+-----------+------------------+------------------+");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

