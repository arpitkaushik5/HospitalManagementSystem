import java.sql.*;

public class checkDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hospital";

        //Database Credentials
        String username = "root";
        String password = "root";

        String query = "SELECT * FROM doctors;";

        //Establish the connection
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            System.out.println("System connected to database");

            //Perform Database operations here
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                //Fetch data by column or index
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");

                System.out.println("ID: " + id + " Name: " + name + " Specialization: " + specialization);
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e){
            System.err.println("Connection Failed: " + e.getMessage());
        }
    }
}
