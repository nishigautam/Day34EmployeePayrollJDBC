package com.payrolljdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileIOService {
    public static final String FILENAME = "EmployeePayrollService.sql";

    public List<EmpPayrollData> readData() {
        List<EmpPayrollData> empPayrollDataList = new ArrayList<EmpPayrollData>();
        try {
            Files.lines(Paths.get(FILENAME)).map(line -> line.trim()).forEach(line -> {
                String[] data = line.split("[a-zA-Z]+ : ");
                int id = Character.getNumericValue(data[1].charAt(0));
                String name = data[2];
                double salary = Double.parseDouble(data[3]);
                EmpPayrollData empPayrollDataObject = new EmpPayrollData(id,name,salary);
                empPayrollDataList.add(empPayrollDataObject);
            });
        } catch (IOException e) {
        }
        return empPayrollDataList;
    }

    public void writeData(List<EmpPayrollData> empPayrollDataList) {
        StringBuffer employeeString = new StringBuffer();
        empPayrollDataList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            employeeString.append(employeeDataString);
        });

        try {
            Files.write(Paths.get(FILENAME), employeeString.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(Paths.get(FILENAME)).count();
        } catch (IOException e) {
        }
        return entries;
    }

    public void printEmployeePayrollData() {
        try {
            Files.lines(Paths.get(FILENAME)).forEach(System.out::println);
        } catch (IOException e) {
        }
    }
}
