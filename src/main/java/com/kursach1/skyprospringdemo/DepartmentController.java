package com.kursach1.skyprospringdemo;

import Interface.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "map")
    public String getAll(){
        return departmentService.getAll().toString();
    }

    @GetMapping(path = "max-salary")
    public String maxSalary(@RequestParam int departmentId){
        return departmentService.employeeWithMaxSalary(departmentId).toString();
    }

    @GetMapping(path = "min-salary")
    public String minSalary(@RequestParam int departmentId){
        return departmentService.employeeWithMinSalary(departmentId).toString();
    }

}
