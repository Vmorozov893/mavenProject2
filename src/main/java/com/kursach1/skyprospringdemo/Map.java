package com.kursach1.skyprospringdemo;

import exeption.EmployeeAlreadyAddedException;
import exeption.EmployeeNotFoundException;
import exeption.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Map {
    private HashMap<String,Employee> map = new HashMap();
    private final int maxCountEmployee = 10;

    public HashMap getMap() {
        return map;
    }

    public Employee addEmployee (String firstName, String lastName){

        if(this.map.size()==maxCountEmployee){
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме!");
        } else if (this.map.get(firstName+lastName)!=null) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть!");
        } else{
            Employee employee = new Employee(firstName, lastName);
            this.map.put(firstName+lastName, employee);
            return this.map.get(firstName+lastName);
        }
    }

    public Employee deleteEmployee(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        if(this.map.get(firstName+lastName)!=null){
            this.map.remove(firstName+lastName);
            return employee;
        }else {
            throw new EmployeeNotFoundException("Сотрудник не найден!");
        }
    }

    public Employee findEmployee(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        if (this.map.get(firstName+lastName)!=null){
            return this.map.get(firstName+lastName);
        }else{
            throw new EmployeeNotFoundException("Сотрудник не найден!");
        }
    }

}
