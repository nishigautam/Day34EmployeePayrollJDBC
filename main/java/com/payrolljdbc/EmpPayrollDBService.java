package com.payrolljdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpPayrollDBService {
    private static EmpPayrollDBService empPayrollDBService;
    private PreparedStatement employeePayrollDataStatement;

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

    public List<EmpPayrollData> getEmpPayrollData(String name) throws ExceptionDB {
        List<EmpPayrollData> empPayrollDataList = new ArrayList<>();
        String sql = String.format("SELECT * FROM employee_payroll WHERE name='%s';",name);
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String empName = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate start = resultSet.getDate("start").toLocalDate();
                empPayrollDataList.add(new EmpPayrollData(id,empName,salary,start));
            }
        } catch (SQLException throwable) {
            throw new ExceptionDB("Cannot establish Connection", ExceptionDB.ExceptionType.CONNECTION_FAILURE);
        }
        return empPayrollDataList;
    }

    private void prepareStatementEmployeeData() {
        try {
            Connection connection = this.getConnection();
            String sql = "SELECT * FROM employee_payroll where name=?";
            employeePayrollDataStatement = connection.prepareStatement(sql);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public int updateEmpData(String name, double salary) {
        return this.updateEmpDataUsingStatement(name, salary);
    }

    private int updateEmpDataUsingStatement(String name, double salary) {
        String sql = String.format(" UPDATE employee_Payroll SET salary = %.2f WHERE name = '%s';", salary, name);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return 0;
    }
}