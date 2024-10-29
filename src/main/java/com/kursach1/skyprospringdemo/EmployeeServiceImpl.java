package com.kursach1.skyprospringdemo;

import Interface.EmployeeService;
import exeption.EmployeeAlreadyAddedException;
import exeption.EmployeeNotFoundException;
import exeption.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final HashMap<String,Employee> map = new HashMap();
    private final int maxCountEmployee = 10;

    public HashMap<String,Employee> getAllEmployees() {
        return map;
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

    public HashMap<String, Employee> getAll() {
        return map;
    }
}
