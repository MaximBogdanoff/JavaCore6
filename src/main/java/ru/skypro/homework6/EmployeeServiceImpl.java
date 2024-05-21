package ru.skypro.homework6;

import Service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int STORAGE_SIZE = 5;
    private final List<Employee> employees = new ArrayList<>(List.of
            (new Employee("Ivan", "Ivanov"),
                    new Employee("Вадим", " Безрукавый"),
                    new Employee("Алексей", " Коток"),
                    new Employee("Константин", " Реунов"),
                    new Employee("Андрей", " Постов")
            ));

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() >= STORAGE_SIZE) {
            throw new EmployeeStorageIsFullException("Список полностью заполнен");
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен в список");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Удаляемый сотрудник не найден");
        }
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }
}
