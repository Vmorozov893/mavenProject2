package com.kursach1.skyprospringdemo;

import Interface.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTests {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private final Map<String, Employee> employees = new HashMap<>() {{
        put("ИльяМуромец", new Employee("Илья", "Муромец", 300_000, 1));
        put("ДобрыняНикитич", new Employee("Добрыня", "Никитич", 200_000, 1));
        put("АлёшаПопович", new Employee("Алёша", "Попович", 100_000, 1));
        put("СоловейРазбойник", new Employee("Соловей", "Разбойник", 100_000, 2));
        put("ЗмейГорыныч", new Employee("Змей", "Горыныч", 200_000, 2));
        put("ЗмейТугарин", new Employee("Змей", "Тугарин", 300_000, 2));
    }};

    @Test
    public void departmentEmployeesTest() {
        int departmentId = 1;
        List<Employee> expectedEmployees = employees.values().stream()
                .filter(e -> e.getDepartment().equals(departmentId))
                .toList();

        Mockito.when(employeeService.getAll()).thenReturn((HashMap<String, Employee>) employees);

        List<Employee> actualEmployees = departmentService.departmentEmployees(departmentId);

        Assertions.assertEquals(expectedEmployees, actualEmployees);

    }

    @Test
    public void sumSalaryTest() {
        Integer departmentId = 1;
        int expectedSum = 600_000;

        Mockito.when(employeeService.getAll()).thenReturn((HashMap<String, Employee>) employees);

        int actualSum = departmentService.sumSalary(departmentId);

        Assertions.assertEquals(expectedSum, actualSum);
    }

    @Test
    public void employeeWithMaxSalaryTest() {
        Integer departmentId = 2;
        Employee expectedEmployee = employees.get("ЗмейТугарин");

        Mockito.when(employeeService.getAll()).thenReturn((HashMap<String, Employee>) employees);

        Employee actualEmployee = departmentService.employeeWithMaxSalary(departmentId);

        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void employeeWithMinSalaryTest() {
        Integer departmentId = 1;
        Employee expectedEmployee = employees.get("АлёшаПопович");

        Mockito.when(employeeService.getAll()).thenReturn((HashMap<String, Employee>) employees);

        Employee actualEmployee = departmentService.employeeWithMinSalary(departmentId);

        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void employeesByDepartmentsTest() {
        Integer departmentId1 = 1;
        Integer departmentId2 = 2;

        List<Employee> expectedEmployeeDepartmentId1 = employees.values().stream()
                .filter(e -> e.getDepartment().equals(departmentId1))
                .toList();
        List<Employee> expectedEmployeeDepartmentId2 = employees.values().stream()
                .filter(e -> e.getDepartment().equals(departmentId2))
                .toList();

        List<List<Employee>> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(expectedEmployeeDepartmentId1);
        expectedEmployees.add(expectedEmployeeDepartmentId2);


        Mockito.when(employeeService.getAll()).thenReturn((HashMap<String, Employee>) employees);

        List<List<Employee>> actualEmployees = departmentService.employeesByDepartments();

        Assertions.assertEquals(expectedEmployees, actualEmployees);

    }


}
