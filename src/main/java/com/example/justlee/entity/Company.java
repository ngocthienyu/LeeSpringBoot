package com.example.justlee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companyID", updatable = false, nullable = false)
    @JsonIgnore
    private Long companyID;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String address;
    @Column(length = 4)
    private int since;
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<Employee> employees;

    public Company() {
    }

    public Company(Long companyID, String name, String address, int since) {
        this.companyID = companyID;
        this.name = name;
        this.address = address;
        this.since = since;
    }

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSince() {
        return since;
    }

    public void setSince(int since) {
        this.since = since;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
