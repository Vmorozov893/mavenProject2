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

    private void addEmployee(int countEmployee){
        for (int i = 1; i <= countEmployee; i++) {
            employeeServiceimpl.addEmployee("Илья" + i, "Муромец", 100_000, 1);
        }
    }
    private Employee addEmployee(Employee employeeToAdd){
        employeeToAdd = employeeServiceimpl.addEmployee(employeeToAdd.getFirstName(), employeeToAdd.getLastName(), employeeToAdd.getSalary(), employeeToAdd.getDepartment());
        return employeeToAdd;
    }
    @Test
    public void addEmployeeTest1() {
        Employee employeeToAdd = new Employee("Илья", "Муромец", 300_000, 1);

        Employee addedEmployee = addEmployee(employeeToAdd);

        Assertions.assertEquals(employeeToAdd, addedEmployee);
    }

    @Test
    public void addEmployeeTest2() {
        int maxCountEmployee = 10;
        addEmployee(maxCountEmployee);

        EmployeeStorageIsFullException thrown = assertThrows(EmployeeStorageIsFullException.class, () -> {
                    employeeServiceimpl.addEmployee("Добрыня", "Никитич", 100_000, 1);
                });

        Assertions.assertEquals("Превышен лимит количества сотрудников в фирме!", thrown.getMessage());
    }
    @Test
    public void addEmployeeTest3() {
        Employee employeeToAdd = new Employee("Добрыня", "Никитич", 100_000, 1);
        addEmployee(employeeToAdd);

        EmployeeAlreadyAddedException thrown = assertThrows(EmployeeAlreadyAddedException.class, () -> {
            addEmployee(employeeToAdd);
                });

        Assertions.assertEquals("Такой сотрудник уже есть!", thrown.getMessage());
    }
    @Test
    public void deleteEmployeeTest1(){
        Employee employeeToAdd = new Employee("Добрыня", "Никитич", 100_000, 1);

        addEmployee(employeeToAdd);

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
        Employee employeeToAdd = new Employee("Добрыня", "Никитич", 100_000, 1);

        addEmployee(employeeToAdd);

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
