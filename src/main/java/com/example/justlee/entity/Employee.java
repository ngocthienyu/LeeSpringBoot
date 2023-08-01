package com.example.justlee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "employeeID", updatable = false, nullable = false)
    private Long employeeID;
    @Column(length = 50)
    private String fullname;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;
    @Column(length = 50)
    private String hometown;
    private LocalDate dob;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "companyID")
    private Company company;
    @Transient
    private Integer age;

    public Employee() {
    }

    public Employee(Long id, String fullname, Gender gender, String hometown, LocalDate dob, Company company) {
        this.employeeID = id;
        this.fullname = fullname;
        this.gender = gender;
        this.hometown = hometown;
        this.dob = dob;
        this.company = company;
        this.age = Period.between(dob, LocalDate.now()).getYears();
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(LocalDate dob) {
        this.age = Period.between(this.dob, LocalDate.now()).getYears();
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", fullname='" + fullname + '\'' +
                ", gender=" + gender +
                ", hometown='" + hometown + '\'' +
                ", dob=" + dob +
                ", company=" + company +
                ", age=" + age +
                '}';
    }
}
