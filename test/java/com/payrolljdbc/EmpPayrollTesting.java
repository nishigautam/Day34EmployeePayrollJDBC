package com.payrolljdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class EmpPayrollTesting {

    @Test
    public void given3EmployeesWhenWrittenToFileShouldMatchNumberOFEmpEntries() {
        EmpPayrollData[] arrayRecords = {
                new EmpPayrollData(1, "Jeff Bezos", 100000.0),
                new EmpPayrollData(2, "Bill Gates", 200000.0),
                new EmpPayrollData(3, "Mark Zukerberg", 300000.0)
        };

        EmpPayrollService empPayrollService = new EmpPayrollService(Arrays.asList(arrayRecords));
        empPayrollService.writeEmployeeData(EmpPayrollService.IOService.FILE_IO);
        empPayrollService.printData();
        Assertions.assertEquals(3, empPayrollService.countEntries(EmpPayrollService.IOService.FILE_IO));
    }

    @Test
    public void given3EmployeesWhenReadFromFileShouldMatchNumberOfEmployeeEntries() {
        EmpPayrollService empPayrollService = new EmpPayrollService();
        empPayrollService.readEmployeeData(EmpPayrollService.IOService.FILE_IO);
        int countEntries = empPayrollService.sizeOfEmpList();
        Assertions.assertEquals(3, countEntries);
    }

    @Test
    public void givenNewSalaryForEmployee_ShouldMatchWhenUpdated() throws ExceptionDB {
        EmpPayrollService empPayrollService = new EmpPayrollService();
        List<EmpPayrollData> empPayrollDataList = empPayrollService.readEmployeePayrollData(EmpPayrollService.IOService.DB_IO);
        empPayrollService.updateEmpSalary("Terrisa", 3000000.00);
        boolean result = empPayrollService.checkEmployeePayrollInSyncWithDB("Terrisa");
        Assertions.assertTrue(result);
    }
}