package com.payrolljdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.payrolljdbc.EmpPayrollService.IOService.DB_IO;

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
        List<EmpPayrollData> empPayrollDataList = empPayrollService.readEmployeePayrollData(DB_IO);
        empPayrollService.updateEmpSalary("Terrisa", 3000000.00);
        boolean result = empPayrollService.checkEmployeePayrollInSyncWithDB("Terrisa");
        Assertions.assertTrue(result);
    }

    @Test
    public void givenDataRange_WhenRetrieved_ShouldMatchEmployeeCount() throws ExceptionDB {
        EmpPayrollService empPayrollService = new EmpPayrollService();
        empPayrollService.readEmployeePayrollData(DB_IO);
        LocalDate start = LocalDate.of(2018,01,01);
        LocalDate endDate = LocalDate.now();
        List<EmpPayrollData> empPayrollDataList = empPayrollService.readEmployeeDataWithGivenDateRange(DB_IO, start,endDate);
        Assertions.assertEquals(3,empPayrollDataList.size());
    }
}