package com.example.demo.repositories;

import com.example.demo.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE (:status is null or e.status = :status)")
    Page<Employee> findAllByParams(@Param("status") boolean status,
                                   Pageable pageable);
}
