package ru.skypro.homework6;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Storage is full")
public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException(String ArraysIsFull) {
        super(ArraysIsFull);
        System.out.println("Storage is full");
    }
}
