package com.kursach1.skyprospringdemo;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Employee {
    private final String lastName;
    private final String firstName;
    private final Integer salary;
    private final Integer department;

    public Employee(String firstName, String lastName, Integer salary, Integer department) {
        this.firstName = StringUtils.capitalize(firstName);
        this.lastName = StringUtils.capitalize(lastName);
        this.salary = salary;
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getDepartment() {
        return department;
    }

    public Integer getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(lastName, employee.lastName) && Objects.equals(firstName, employee.firstName) && Objects.equals(salary, employee.salary) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, salary, department);
    }

    @Override
    public String toString() {
        return "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", salary=" + salary +
                ", department=" + department + "\n";
    }

}
