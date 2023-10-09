package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/ex1"; // Replace with your database URL
        String username = "postgres"; // Replace with your database username
        String password = "root"; // Replace with your database password
        int employeeId = 1; // Replace with the desired employee ID

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT first_name, last_name, gender, city_id FROM employee WHERE employee_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, employeeId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                        String gender = resultSet.getString("gender");
                        String city = resultSet.getString("city_id");

                        System.out.println("Employee Information:");
                        System.out.println("First Name: " + firstName);
                        System.out.println("Last Name: " + lastName);
                        System.out.println("Gender: " + gender);
                        System.out.println("City: " + city);
                    } else {
                        System.out.println("Employee not found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
