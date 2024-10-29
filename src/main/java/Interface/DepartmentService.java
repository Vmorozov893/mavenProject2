package Interface;

import com.kursach1.skyprospringdemo.Employee;

import java.util.HashMap;

public interface DepartmentService {
    HashMap<String, Employee> getAll();
    Employee employeeWithMaxSalary(int departmentId);
    Employee employeeWithMinSalary(int departmentId);
}
