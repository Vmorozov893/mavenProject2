package com.kursach1.skyprospringdemo;

import Interface.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "{departmentId}/employees")
    public String departmentEmployees(@PathVariable Optional<Integer> departmentId) {
        return departmentService.departmentEmployees(departmentId.get()).toString();
    }

    @GetMapping(path = "{departmentId}/salary/sum")
    public int departmentSumSalary(@PathVariable Integer departmentId) {
        return departmentService.sumSalary(departmentId);
    }

    @GetMapping(path = "{departmentId}/salary/max")
    public String maxSalary(@PathVariable Integer departmentId) {
        return departmentService.employeeWithMaxSalary(departmentId).toString();
    }

    @GetMapping(path = "{departmentId}/salary/min")
    public String minSalary(@PathVariable Integer departmentId) {
        return departmentService.employeeWithMinSalary(departmentId).toString();
    }

    @GetMapping(path = "employees")
    public String getAll() {
        return departmentService.employeesByDepartments().toString();
    }
}
