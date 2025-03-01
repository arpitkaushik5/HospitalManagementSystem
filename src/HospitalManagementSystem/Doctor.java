package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection connection;

    public Doctor(Connection connection){
        this.connection = connection;
    }

    public void viewDoctor(){
        String query = "SELECT * FROM doctors";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Doctors Data:");
            System.out.println("+------------+-----------------+----------------+");
            System.out.println("| Doctor ID  | Doctor Name     | Specialization |");
            System.out.println("+------------+-----------------+----------------+");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");

                System.out.printf("| %-10s | %-15s | %-14s |\n", id, name, specialization);
                System.out.println("+------------+-----------------+----------------+");
            }
        } catch (SQLException e){
            System.err.println("Problem in Database Connection: " + e.getMessage());
        }
    }

    public boolean getDoctorById(int id){
        String query = "SELECT * FROM doctors WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            } else {
                return false;
            }

        } catch (SQLException e){
            System.err.println("Problem in Database Connection: " + e.getMessage());
        }
        return false;
    }
}
