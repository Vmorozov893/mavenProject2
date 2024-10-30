package Interface;

import com.kursach1.skyprospringdemo.Employee;

import java.util.HashMap;
import java.util.List;

public interface DepartmentService {
    HashMap<String, Employee> getAll();
    Employee employeeWithMaxSalary(Integer departmentId);
    Employee employeeWithMinSalary(Integer departmentId);
    List<Employee> departmentEmployees (Integer departmentId);
    List< List < Employee > > employeesByDepartments();
}
