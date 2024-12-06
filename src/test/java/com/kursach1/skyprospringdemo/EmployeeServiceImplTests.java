package com.kursach1.skyprospringdemo;

import exeption.EmployeeAlreadyAddedException;
import exeption.EmployeeNotFoundException;
import exeption.EmployeeStorageIsFullException;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceImplTests {

    private EmployeeServiceImpl employeeServiceimpl;

    @BeforeEach
    public void clear() {
        employeeServiceimpl = new EmployeeServiceImpl();
    }

    private Employee addEmployee(){
        return new Employee("Илья", "Муромец", 300_000, 1);
    }
    @Test
    public void addEmployeeTest1() {
        Employee employeeToAdd = addEmployee();

        Employee addedEmployee = employeeServiceimpl.addEmployee(employeeToAdd.getFirstName(), employeeToAdd.getLastName(), employeeToAdd.getSalary(), employeeToAdd.getDepartment());

        Assertions.assertEquals(employeeToAdd, addedEmployee);
    }

    @Test
    public void addEmployeeTest2() {
        for (int i = 1; i <= employeeServiceimpl.getMaxCountEmployee(); i++) {
            employeeServiceimpl.addEmployee("Илья" + i, "Муромец", 100_000, 1);
        }

        EmployeeStorageIsFullException thrown = assertThrows(EmployeeStorageIsFullException.class, () -> {
                    employeeServiceimpl.addEmployee("Добрыня", "Никитич", 100_000, 1);
                });

        Assertions.assertEquals("Превышен лимит количества сотрудников в фирме!", thrown.getMessage());
    }
    @Test
    public void addEmployeeTest3() {
        employeeServiceimpl.addEmployee("Добрыня", "Никитич", 100_000, 1);

        EmployeeAlreadyAddedException thrown = assertThrows(EmployeeAlreadyAddedException.class, () -> {
                    employeeServiceimpl.addEmployee("Добрыня", "Никитич", 100_000, 1);
                });

        Assertions.assertEquals("Такой сотрудник уже есть!", thrown.getMessage());
    }
    @Test
    public void deleteEmployeeTest1(){
        Employee employeeToAdd = addEmployee();

        employeeServiceimpl.addEmployee(employeeToAdd.getFirstName(), employeeToAdd.getLastName(), employeeToAdd.getSalary(), employeeToAdd.getDepartment());

        Employee deletedEmployee = employeeServiceimpl.deleteEmployee(employeeToAdd.getFirstName(), employeeToAdd.getLastName());

        Assertions.assertEquals(employeeToAdd, deletedEmployee);
    }

    @Test
    public void deleteEmployeeTest2(){

        EmployeeNotFoundException thrown = assertThrows(EmployeeNotFoundException.class, () -> {
                    employeeServiceimpl.deleteEmployee("Добрыня","Никитич");
                });
        Assertions.assertEquals("Сотрудник не найден!", thrown.getMessage());
    }

    @Test
    public void findEmployeeTest1(){
        Employee employeeToAdd = addEmployee();

        employeeServiceimpl.addEmployee(employeeToAdd.getFirstName(), employeeToAdd.getLastName(), employeeToAdd.getSalary(), employeeToAdd.getDepartment());

        Employee findedEmployee = employeeServiceimpl.findEmployee(employeeToAdd.getFirstName(), employeeToAdd.getLastName());

        Assertions.assertEquals(employeeToAdd, findedEmployee);
    }

    @Test
    public void findEmployeeTest2(){

        EmployeeNotFoundException thrown = Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeServiceimpl.findEmployee("Добрыня","Никитич");
        });
        Assertions.assertEquals("Сотрудник не найден!", thrown.getMessage());

    }


}
