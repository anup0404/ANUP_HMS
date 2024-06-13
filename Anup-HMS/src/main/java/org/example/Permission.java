package org.example;

import Operation.Appointment;
import Operation.Doctors;
import Operation.Patients;
import OperationsMongodb.Doctors_details;
import OperationsMongodb.Patients_details;

import java.util.Scanner;

public class Permission {
    Patients_details pd;
    Doctors_details dd;
    Appointment appointment;
    Patients patients;
    Doctors doctors;
    Scanner scanner;
    public Permission(Patients_details pd, Doctors_details dd, Appointment appointment, Patients patients, Doctors doctors,Scanner scanner) {
        this.dd=dd;
        this.appointment=appointment;
        this.pd=pd;
        this.patients=patients;
        this.doctors=doctors;
        this.scanner=scanner;
    }

    public  void worker_option(){
       while(true){
           System.out.println("========== HOSPITAL MANAGEMENT SYSTEM ==========");
           System.out.println("1. Add Patient ");
           System.out.println("2. view Patients ");
           System.out.println("3. Update Patients");
           System.out.println("4. View Doctors ");
           System.out.println("5. Book Appointment");
           System.out.println("6. Update Appointment");
           System.out.println("7. View Appointments");
           System.out.println("8. Insert the patients Details");
           System.out.println("9. View the patients Details");
           System.out.println("10. Update the patients Details");
           System.out.println("11. Delete the patients Details");
           System.out.println("12. View the Doctors Details:");
           System.out.println("13. Exits");
           System.out.print("Enter your choice : ");
           int choice= scanner.nextInt();
           scanner.nextLine();
           switch (choice){
               case 1:
                   // add patient
                   patients.add_patient();
                   break;
               case 2:
                   //view patient
                   patients.view_patient();
                   break;
               case 3:
                   // update patients;
                   patients.update_patients();
                   break;
               case 4:
                   // view doctors
                   doctors.view_doctors();
                   break;
               case 5:
                   //book appointment
                   appointment.book_appointment();
                   break;
               case 6:
                   // update appointments;
                   appointment.update_appointment();
                   break;
               case 7:
                   // view appointments;
                   appointment.view_appointments();
                   break;
               case 8:
                   // insert_patients_details;
                   pd.insert_data();
                   break;
               case 9:
                   // view_patients;
                   pd.view_patients();
                   break;
               case 10:
                   // update_patients_details;
                   pd.update_details();
                   break;
               case 11:
                   // delete_patients_details;
                   pd.delete_patients();
                   break;
               case 12:
                   // view doctors details
                   dd.view_doctors();
                   break;
               case 13:return;
               default:
                   System.out.println("Please Enter Valid choice  !!!!!!!!");
           }
       }
   }
   public void admin_option(){
       while(true){
           System.out.println("========== HOSPITAL MANAGEMENT SYSTEM ==========");
           System.out.println("1. Add Patient ");
           System.out.println("2. view Patients ");
           System.out.println("3. Update Patients");
           System.out.println("4. View Doctors ");
           System.out.println("5. Book Appointment");
           System.out.println("6. Update Appointment");
           System.out.println("7. View Appointments");
           System.out.println("8. Insert the patients Details");
           System.out.println("9. View the patients Details");
           System.out.println("10. Update the patients Details");
           System.out.println("11. Delete the patients Details");
           System.out.println("12. View the Doctors Details:");
           System.out.println("13. Insert he doctors Details");
           System.out.println("14. Update the doctors Details");
           System.out.println("15. Delete the doctors Details");
           System.out.println("16. Exits");
           System.out.print("Enter your choice : ");
           int choice= scanner.nextInt();
           scanner.nextLine();
           switch (choice){
               case 1:
                   // add patient
                   patients.add_patient();
                   break;
               case 2:
                   //view patient
                   patients.view_patient();
                   break;
               case 3:
                   // update patients;
                   patients.update_patients();
                   break;
               case 4:
                   // view doctors
                   doctors.view_doctors();
                   break;
               case 5:
                   //book appointment
                   appointment.book_appointment();
                   break;
               case 6:
                   // update appointments;
                   appointment.update_appointment();
                   break;
               case 7:
                   // view appointments;
                   appointment.view_appointments();
                   break;
               case 8:
                   // insert_patients_details;
                   pd.insert_data();
                   break;
               case 9:
                   // view_patients;
                   pd.view_patients();
                   break;
               case 10:
                   // update_patients_details;
                   pd.update_details();
                   break;
               case 11:
                   // delete_patients_details;
                   pd.delete_patients();
                   break;
               case 12:
                   // view doctors details
                   dd.view_doctors();
                   break;
               case 13:
                   // insert doctors details
                   dd.insert_datails();
                   break;
               case 14:
                   // update doctors details
                   dd.update_doctors();
                   break;
               case 15:
                   // delete doctors details
                   dd.delete_doctors();
                   break;
               case 16:return;
               default:
                   System.out.println("Please Enter Valid choice  !!!!!!!!");
           }
       }




   }
}
