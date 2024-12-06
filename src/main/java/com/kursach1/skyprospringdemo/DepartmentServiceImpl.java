package com.kursach1.skyprospringdemo;

import Interface.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> departmentEmployees(Integer departmentId) {
        return employeeService.getAll().values().stream()
                .filter(e -> e.getDepartment().equals(departmentId))
                .toList();
    }

    public int sumSalary(Integer departmentId) {
        return employeeService.getAll().values().stream()
                .filter(e -> e.getDepartment().equals(departmentId))
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee employeeWithMaxSalary(Integer departmentId) {
        return employeeService.getAll().values().stream()
                .filter(e -> e.getDepartment().equals(departmentId))
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    public Employee employeeWithMinSalary(Integer departmentId) {
        return employeeService.getAll().values().stream()
                .filter(e -> e.getDepartment().equals(departmentId))
                .min(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    public Map<Integer, List<Employee>> employeesByDepartments() {
        return employeeService.getAll().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

    }


}
