package com.payrolljdbc;

import java.time.LocalDate;
import java.util.Objects;

public class EmpPayrollData {
    public int id;
    public String name;
    public double salary;
    public LocalDate date;

    public EmpPayrollData(Integer id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public EmpPayrollData(Integer id, String name, Double salary,LocalDate date) {
        this(id,name,salary);
        this.date = date;
    }

    @Override
    public String toString() {
        return "EmpPayrollData{" +
                "employeeId=" + id + ", employeeName=" + name + ", employeeSalary" + salary + ", startDate=" + date + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        EmpPayrollData that = (EmpPayrollData) obj;
        return id == that.id && Double.compare(that.salary, salary) == 0 && Objects.equals(name, that.name) && Objects.equals(date, that.date);
    }
}
