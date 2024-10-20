package com.kursach1.skyprospringdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    //EmployeeService employeeService = new EmployeeService();
    Map map = new Map();
    @GetMapping(path = "add")
    public String add(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName){
        return map.addEmployee(firstName,lastName).toString();
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
        return map.getMap().toString();
    }
}
