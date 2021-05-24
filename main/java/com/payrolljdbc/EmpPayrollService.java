package com.payrolljdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmpPayrollService {

    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }

    public static final Scanner input = new Scanner(System.in);
    private List<EmpPayrollData> empPayrollDataList;

    public EmpPayrollService() {
        this.empPayrollDataList = new ArrayList<EmpPayrollData>();
    }

    public EmpPayrollService(List<EmpPayrollData> empPayrollDataList) {
        this.empPayrollDataList = empPayrollDataList;
    }

    public int sizeOfEmpList() {
        return this.empPayrollDataList.size();
    }

    public List<EmpPayrollData> readEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.DB_IO))
            this.empPayrollDataList = new EmpPayrollDBService().readData();
        return this.empPayrollDataList;
    }

    public void updateEmpSalary(String name, double salary) {
        int result = new EmpPayrollDBService().updateEmpData(name,salary);
        if (result == 0) {
            try {
                throw new SQLFailedException("Query is failed.");
            } catch (SQLFailedException e) {
                e.printStackTrace();
            }
        }
        EmpPayrollData empPayrollData = this.getEmpPayrollData(name);
        if (empPayrollDataList != null)
            empPayrollData.salary = salary;
    }

    private EmpPayrollData getEmpPayrollData(String name) {
        return this.empPayrollDataList.stream().filter(empPayrollDataItem -> empPayrollDataItem.name.equals(name))
                .findFirst().orElse(null);
    }

    public boolean checkEmployeePayrollInSyncWithDB(String name) throws ExceptionDB {
        List<EmpPayrollData> empPayrollDataList = new EmpPayrollDBService().getEmpPayrollData(name);
        return empPayrollDataList.get(0).equals(getEmpPayrollData(name));
    }

    public List<EmpPayrollData> readEmployeeDataWithGivenDateRange(IOService dbIo, LocalDate start, LocalDate endDate) {
        return null;
    }

    public void readEmployeeData(IOService ioType) {
        if(ioType.equals(IOService.CONSOLE_IO)) {
            System.out.println("Enter Employee ID:");
            int id = input.nextInt();
            System.out.println("Enter Employee Name:");
            String name = input.nextLine();
            System.out.println("Enter Employee Salary:");
            double salary = input.nextDouble();
            System.out.println("Enter Employee startDate :");
            LocalDate start = LocalDate.parse(input.next());
            EmpPayrollData newEmployee = new EmpPayrollData(id, name,salary,start);
            empPayrollDataList.add(newEmployee);
        } else if( ioType.equals(IOService.FILE_IO)) {
            this.empPayrollDataList = new FileIOService().readData();
        }
    }

    public void writeEmployeeData(IOService ioType) {
        if(ioType.equals(IOService.CONSOLE_IO)) {
            for (EmpPayrollData obj : empPayrollDataList)
                System.out.println(obj.toString());
        } else if(ioType.equals(IOService.FILE_IO)) {
            new FileIOService().writeData(empPayrollDataList);
        }
    }

    public long countEntries(IOService ioType) {
        if(ioType.equals(IOService.FILE_IO))
            return new FileIOService().countEntries();
        return 0;
    }

    public void printData() {
        new FileIOService().printEmployeePayrollData();
    }
}