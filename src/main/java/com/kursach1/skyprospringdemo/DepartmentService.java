package com.kursach1.skyprospringdemo;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    EmployeeController Emp = new EmployeeController();
    Map map = Emp.getMap();
    List <Employee> array = map.getAllEmployees();
    public Employee maxSalary(int num) {
        List <Integer> listOfSalary = array.stream()
                .map(e->e.getSalary())
                .collect(Collectors.toList());
        Collections.sort(listOfSalary);
        if(listOfSalary.isEmpty()){
            throw new RuntimeException("Employees is empty!");
        }
        Integer maxSalary = listOfSalary.get(listOfSalary.size()-1);
        Employee employee = array.stream()
                .filter(e->e.getSalary() == maxSalary)
                .findFirst()
                .orElseThrow();
        return employee;
    }
}
