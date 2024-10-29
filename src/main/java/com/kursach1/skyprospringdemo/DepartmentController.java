package com.kursach1.skyprospringdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("departments")
public class DepartmentController {
    DepartmentService serv = new DepartmentService();
    @GetMapping(path = "max-salary")
    public String add(@RequestParam(required = false) int departmentId){
        return serv.maxSalary(departmentId).toString();
    }
}
