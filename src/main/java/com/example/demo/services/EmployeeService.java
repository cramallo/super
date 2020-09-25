package com.example.demo.services;

import com.example.demo.dtos.EmployeePageDto;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.requests.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(final EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .address(employeeRequest.getAddress())
                .bank(employeeRequest.getBank())
                .cbu(employeeRequest.getCbu())
                .country(employeeRequest.getCountry())
                .province(employeeRequest.getProvince())
                .city(employeeRequest.getCity())
                .address(employeeRequest.getAddress())
                .dateOfAdmission(employeeRequest.getDateOfAdmission())
                .dni(employeeRequest.getDni())
                .gender(employeeRequest.getGender())
                .name(employeeRequest.getName())
                .phone(employeeRequest.getPhone())
                .position(employeeRequest.getPosition())
                .salary(employeeRequest.getSalary())
                .status(true)
                .build();
        return this.employeeRepository.save(employee);
    }

    public Employee getEmployee(final long id) throws Exception {
        try {
            Optional<Employee> employee = this.employeeRepository.findById(id);
            if (employee.isPresent()) {
                return employee.get();
            }
            return null;
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }

    public EmployeePageDto getEmployees(final boolean status, final Pageable pageable) throws Exception {
        try {
            final Page<Employee> employee = this.employeeRepository.findAllByParams(status, pageable);
            return buildEmployeePageDto(employee);
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }

    /*public Employee updateEmployee(final Long id, final EmployeeRequest employeeRequest) throws Exception {
        try {
            Employee employeeFound = getEmployee(id);
            employeeFound.(employeeRequest.getBirthday());
            employeeFound.setName(updatedUser.getName());
            employeeFound.setUsername(updatedUser.getUsername());
            return userRepository.save(userFound);
        } catch (Exception serverError) {
            throw new Exception("Internal server error");
        }
    }*/

    private EmployeePageDto buildEmployeePageDto(final Page<Employee> employee) {
        return EmployeePageDto.builder()
                .actualPage(employee.getNumber() + 1)
                .numberOfElementsPerPage(employee.getSize())
                .totalPages(employee.getTotalPages())
                .employees(employee.getContent())
                .build();
    }
}
