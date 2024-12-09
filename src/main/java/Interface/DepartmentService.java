package Interface;

import com.kursach1.skyprospringdemo.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<Employee> departmentEmployees(Integer departmentId);

    int sumSalary(Integer departmentId);

    Employee employeeWithMaxSalary(Integer departmentId);

    Employee employeeWithMinSalary(Integer departmentId);

    Map<Integer,List<Employee>> employeesByDepartments();

}
