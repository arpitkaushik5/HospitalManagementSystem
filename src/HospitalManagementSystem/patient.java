package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class patient {
    private Connection connection;
    private Scanner scanner;


    public patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient(){
        System.out.println("Enter Patient Name: ");
        String name = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Patient Age: ");
        int age = scanner.nextInt();
        System.out.println("Enter Patient Gender: ");
        String gender = scanner.next();
        scanner.nextLine();

        try {
            String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows>0){
                System.out.println("Data added successfully");
            } else {
                System.out.println("Failed to add patient!!");
            }

        } catch (SQLException e){
            System.err.println("Problem in Database Connection: " + e.getMessage());
        }
    }

    public void viewPatient(){
        String query = "SELECT * FROM patients";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("+------------+------------+------+--------+");
            System.out.println("| Patient ID | Name       | Age  | Gender |");
            System.out.println("+------------+------------+------+--------+");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");

                System.out.printf("| %-10s | %-10s | %-4s | %-6s |\n", id, name, age,gender);
                System.out.println("+------------+------------+------+--------+");
            }


        } catch (SQLException e){
            System.err.println("Problem in Database Connection: " + e.getMessage());
        }
    }

    public boolean getPatientById(int id){
        String query = "SELECT * FROM patients WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e){
            System.err.println("Problem in Database Connection: " + e.getMessage());
        }
        return false;
    }
}
