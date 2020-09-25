package com.example.demo.dtos;

import com.example.demo.models.Employee;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class EmployeePageDto {
    List<Employee> employees;
    private int actualPage;
    private int totalPages;
    private int numberOfElementsPerPage;
}
