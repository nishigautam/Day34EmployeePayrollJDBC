package com.payrolljdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpPayrollDBService {

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String userName = "root";
        String password = "root";
        Connection connection;
        System.out.println("Database Connecting :" + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Successfully Connected..!!" + connection);
        return connection;
    }
    public List<EmpPayrollData> readData() {
        String sql = "SELECT * FROM employee_payroll;";
        List<EmpPayrollData> empPayrollDataList = new ArrayList<>();
        try(Connection connection = this.getConnection();) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate start = resultSet.getDate("start").toLocalDate();
                empPayrollDataList.add(new EmpPayrollData(id, name,salary,start));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return empPayrollDataList;
    }
}