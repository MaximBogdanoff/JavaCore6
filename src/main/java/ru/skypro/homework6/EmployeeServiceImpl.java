package ru.skypro.homework6;

import Service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int STORAGE_SIZE = 5;
    private final Map<String, Employee> employees = new HashMap<>((Map) List.of
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
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен в список");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Удаляемый сотрудник не найден");
        }
        return employees.remove(employee.getFullName());
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employees.get(employee.getFullName());
    }

    @Override
    public List<Employee> getAllEmployees() {
        return (List<Employee>) Collections.unmodifiableCollection(employees.values());
    }
}
