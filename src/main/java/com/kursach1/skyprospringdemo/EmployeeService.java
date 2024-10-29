package com.kursach1.skyprospringdemo;

import exeption.EmployeeAlreadyAddedException;
import exeption.EmployeeNotFoundException;
import exeption.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private List<Employee> employeesArray = new ArrayList<>();
    private final int maxCountEmployee = 10;

    public List<Employee> getEmployeesArray() {
        return employeesArray;
    }

    public Employee addEmployee(String firstName, String lastName, Integer salary, Integer department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (this.employeesArray.size() == maxCountEmployee) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме!");
        } else if (this.employeesArray.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть!");
        } else {
            this.employeesArray.add(employee);
            return employee;
        }

    }

    public Employee deleteEmployee(String firstName, String lastName) {
        Optional<Employee> employee = this.employeesArray.stream()
                .filter(e -> (e.getFirstName() + e.getLastName()).equals(firstName + lastName))
                .findFirst();
        this.employeesArray.removeIf(e -> (e.getFirstName() + e.getLastName()).equals(firstName + lastName));
        return employee.orElseThrow(() -> new RuntimeException("Сотрудник не найден!"));
    }

    public Employee findEmployee(String firstName, String lastName) {
        Optional<Employee> employee = this.employeesArray.stream()
                .filter(e -> (e.getFirstName() + e.getLastName()).equals(firstName + lastName))
                .findFirst();

        return employee.orElseThrow(() -> new RuntimeException("Сотрудник не найден!"));
    }

}
