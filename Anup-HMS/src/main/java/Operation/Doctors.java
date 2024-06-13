package Operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctors{
    private Connection connection;
    public Doctors(Connection connection) {
        this.connection = connection;
    }


    // viewing the details of doctors;

    public  void  view_doctors() {
        String quary = "select * from doctors";
        try {
            PreparedStatement pst= connection.prepareStatement(quary);
            ResultSet resultSet =pst.executeQuery();
            System.out.println("Details of doctors : ");
            System.out.println("+------------+--------------------+---------------------+");
            System.out.println("| Operations.Doctors Id | Name    | Specialization      |");
            System.out.println("+------------+--------------------+--------+-------------+");
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String specialization=resultSet.getString("specialization");
                System.out.printf("|%-12s|%-20s|%-21s|\n",id,name,specialization);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // checking that this id doctors exits in our databases or not;


    public boolean get_doctor_by_id(int doctor_id){
        try{
            String quary="select * from doctors where id=?";
            PreparedStatement psmt=connection.prepareStatement(quary);
            psmt.setInt(1, doctor_id);
            ResultSet resultSet=psmt.executeQuery();
            if(resultSet.next())return true;
            else return false;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}