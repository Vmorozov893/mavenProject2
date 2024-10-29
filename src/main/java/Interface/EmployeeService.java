package Interface;

import com.kursach1.skyprospringdemo.Employee;

import java.util.HashMap;

public interface EmployeeService {
    HashMap<String, Employee> getAllEmployees();
    Employee addEmployee (String firstName, String lastName,int salary,int department);
    Employee deleteEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);

}