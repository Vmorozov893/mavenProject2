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

    public HashMap<String, Employee> getAll(){
        return employeeService.getAll();
    }

    public Employee employeeWithMaxSalary(int departmentId){
        List <Integer> listOfSalary = employeeService.getAll().values().stream()
                .filter(e->e.getDepartment().equals(departmentId))
                .map(Employee::getSalary)
                .sorted().toList();
        Integer maxSalary = listOfSalary.get(listOfSalary.size()-1);
        return employeeService.getAll().values().stream()
                .filter(e->e.getDepartment().equals(departmentId))
                .filter(e->e.getSalary().equals(maxSalary))
                .findFirst()
                .orElseThrow();
    }

    public Employee employeeWithMinSalary(int departmentId){
        List <Integer> listOfSalary = employeeService.getAll().values().stream()
                .filter(e->e.getDepartment().equals(departmentId))
                .map(Employee::getSalary)
                .sorted().toList();
        Integer minSalary = listOfSalary.get(0);
        return employeeService.getAll().values().stream()
                .filter(e->e.getDepartment().equals(departmentId))
                .filter(e->e.getSalary().equals(minSalary))
                .findFirst()
                .orElseThrow();
    }

}
