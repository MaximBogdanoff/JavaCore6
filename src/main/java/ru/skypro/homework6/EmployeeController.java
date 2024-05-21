package ru.skypro.homework6;

import Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        try {
            employeeService.addEmployee(firstName, lastName);
        } catch (EmployeeStorageIsFullException e) {
            throw new RuntimeException();
        } catch (EmployeeAlreadyAddedException e) {
            throw new RuntimeException();
        }
        Employee employee = new Employee(firstName, lastName);
        return employee;
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        try {
            employeeService.removeEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException();
        }
        Employee employee = new Employee(firstName, lastName);
        return employee;
    }

    @GetMapping(path = "/find")

    public Employee findEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        try {
            employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException();
        }
        Employee employee = new Employee(firstName, lastName);
        return employee;

    }

    @GetMapping(path = "/print")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}