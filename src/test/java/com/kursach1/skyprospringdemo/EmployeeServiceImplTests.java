package com.kursach1.skyprospringdemo;

import exeption.EmployeeAlreadyAddedException;
import exeption.EmployeeNotFoundException;
import exeption.EmployeeStorageIsFullException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeServiceImplTests {

    private EmployeeServiceImpl employeeServiceimpl;

    @BeforeEach
    public void clear() {
        employeeServiceimpl = new EmployeeServiceImpl();
    }

    @Test
    public void addEmployeeTest1() {
        Employee employeeToAdd = new Employee("Илья", "Муромец", 300_000, 1);

        Employee addedEmployee = employeeServiceimpl.addEmployee(employeeToAdd.getFirstName(), employeeToAdd.getLastName(), employeeToAdd.getSalary(), employeeToAdd.getDepartment());

        Assertions.assertEquals(employeeToAdd, addedEmployee);
    }

    @Test
    public void addEmployeeTest2() {
        for (int i = 1; i <= employeeServiceimpl.getMaxCountEmployee(); i++) {
            employeeServiceimpl.addEmployee("Илья" + i, "Муромец", 100_000, 1);
        }

        Assertions.assertThrows(
                EmployeeStorageIsFullException.class,
                () -> {
                    employeeServiceimpl.addEmployee("Добрыня", "Никитич", 100_000, 1);
                });
    }
    @Test
    public void addEmployeeTest3() {
        employeeServiceimpl.addEmployee("Добрыня", "Никитич", 100_000, 1);

        Assertions.assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> {
                    employeeServiceimpl.addEmployee("Добрыня", "Никитич", 100_000, 1);
                });
    }
    @Test
    public void deleteEmployeeTest1(){
        Employee employeeToAdd = new Employee("Илья", "Муромец", 300_000, 1);

        Employee addedEmployee = employeeServiceimpl.addEmployee(employeeToAdd.getFirstName(), employeeToAdd.getLastName(), employeeToAdd.getSalary(), employeeToAdd.getDepartment());

        Employee deletedEmployee = employeeServiceimpl.deleteEmployee(employeeToAdd.getFirstName(), employeeToAdd.getLastName());

        Assertions.assertEquals(employeeToAdd, deletedEmployee);
    }

    @Test
    public void deleteEmployeeTest2(){

        Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> {
                    employeeServiceimpl.deleteEmployee("Добрыня","Никитич");
                });
    }

    @Test
    public void findEmployeeTest1(){
        Employee employeeToAdd = new Employee("Илья", "Муромец", 300_000, 1);

        Employee addedEmployee = employeeServiceimpl.addEmployee(employeeToAdd.getFirstName(), employeeToAdd.getLastName(), employeeToAdd.getSalary(), employeeToAdd.getDepartment());

        Employee findedEmployee = employeeServiceimpl.findEmployee(employeeToAdd.getFirstName(), employeeToAdd.getLastName());

        Assertions.assertEquals(employeeToAdd, findedEmployee);
    }

    @Test
    public void findEmployeeTest2(){

        Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> {
                    employeeServiceimpl.findEmployee("Добрыня","Никитич");
                });
    }


}
