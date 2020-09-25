package com.example.demo.controllers;

import com.example.demo.dtos.EmployeePageDto;
import com.example.demo.models.Employee;
import com.example.demo.requests.EmployeeRequest;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee buildEmployee(@RequestBody final EmployeeRequest employeeRequest) {
        return this.employeeService.saveEmployee(employeeRequest);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") final Long id) throws Exception {
        return this.employeeService.getEmployee(id);
    }

    @GetMapping
    public EmployeePageDto getEmployees(@RequestParam(value = "status", required = false) boolean status,
                                        final Pageable pageable) throws Exception {
        return this.employeeService.getEmployees(status, pageable);
    }
}
