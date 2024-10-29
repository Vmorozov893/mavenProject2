package com.kursach1.skyprospringdemo;

import exeption.EmployeeAlreadyAddedException;
import exeption.EmployeeNotFoundException;
import exeption.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class Map {
    private HashMap<String,Employee> map = new HashMap();
    private final int maxCountEmployee = 10;

    public List<Employee> getAllEmployees() {
        return map.values().stream().toList();
    }

    public Employee addEmployee (String firstName, String lastName,int salary,int department){

        if(this.map.size()==maxCountEmployee){
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме!");
        } else if (this.map.get(firstName+lastName)!=null) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть!");
        } else{
            Employee employee = new Employee(firstName, lastName,salary,department);
            this.map.put(firstName+lastName, employee);
            return this.map.get(firstName+lastName);
        }
    }

    public Employee deleteEmployee(String firstName, String lastName){
        if(this.map.get(firstName+lastName)!=null){
            Employee employee = this.map.get(firstName+lastName);
            this.map.remove(firstName+lastName);
            return employee;
        }else {
            throw new EmployeeNotFoundException("Сотрудник не найден!");
        }
    }

    public Employee findEmployee(String firstName, String lastName){
        if (this.map.get(firstName+lastName)!=null){
            return this.map.get(firstName+lastName);
        }else{
            throw new EmployeeNotFoundException("Сотрудник не найден!");
        }
    }

}
