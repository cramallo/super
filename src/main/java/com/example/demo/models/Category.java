package com.example.demo.models;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AccessLevel;

import javax.persistence.Entity;

public class Category {
    private long id;
    private String jobName;
    private float salary;
    private int hoursPerWeek;
}
