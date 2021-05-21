package com.payrolljdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class EmpPayrollDB {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String userName = "root";
        String password = "root";
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded..");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        listDrivers();

        try {
            System.out.println("Connecting to database :" + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL,userName,password);
            System.out.println("Connection is successful!" + connection);
          } catch (SQLException e) {
            e.printStackTrace();
          }
    }

    private static void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            Driver driverClass = driverList.nextElement();
            System.out.println("Driver:" + driverClass.getClass().getName());
        }
    }
}