package com.example.demo.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String dni;
    private String gender;
    private String address;
    private String city;
    private String province;
    private String country;
    private String position;
    private String phone;
    private boolean status;
    private String bank;
    private String cbu;
    private String dateOfAdmission;
    private String workingHours;
    private float salary;
}
