package com.kursach1.skyprospringdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    //EmployeeService employeeService = new EmployeeService();
    //public Map map = new Map();
    private final EmployeeServiceImpl map;
    public EmployeeController(EmployeeServiceImpl map) {
        this.map = map;
    }
    @GetMapping(path = "add")
    public String add(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) Integer salary, @RequestParam(required = false) Integer department){
        return map.addEmployee(firstName,lastName,salary,department).toString();
    }

    @GetMapping(path = "remove")
    public String remove(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName){
        return map.deleteEmployee(firstName,lastName).toString();
    }

    @GetMapping(path = "find")
    public String find(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName){
        return map.findEmployee(firstName,lastName).toString();
    }

    @GetMapping(path = "map")
    public String list(){
        return map.getAll().values().toString();
    }

    @GetMapping(path = "fillOutTheMap")
    public String fillOutTheMap(){
        map.addEmployee("Ivanov","Ivan",65_000,3);
        map.addEmployee("Petrov","Petr",81_000,2);
        map.addEmployee("Voronov","Denis",33_000,1);
        map.addEmployee("Mironov","Nikolay",105_000,3);
        map.addEmployee("Demidov","Michael",90_000,2);
        map.addEmployee("Barinov","Victor",100_000,1);
        map.addEmployee("Lavrov","Maksim",55_000,3);
        return "Map is full!";
    }

    public EmployeeServiceImpl getMap() {
        return map;
    }

}
