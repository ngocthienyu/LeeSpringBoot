package com.example.justlee.service;

import com.example.justlee.repositoryImp.EmployeeRepositoryImp;
import com.example.justlee.entity.Employee;
import com.example.justlee.entity.Gender;
import com.example.justlee.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepositoryImp employeeRepositoryImp;

    public List<Employee> getAllEmployees(){
        log.info("Start call JPA!");
        return employeeRepositoryImp.findAll();
    }
    public Employee addEmployee(Employee employee){
        return employeeRepositoryImp.save(employee);
    }
    public List<Employee> getEmployeesByName(String name){
        return employeeRepositoryImp.findByFullname(name);
    }
    public Employee getEmployeesByID(Long id){
       log.info("Start call JPA!");
       if(employeeRepositoryImp.findById(id).isPresent()){
           return employeeRepositoryImp.findById(id).get();
       }
       log.error("Can't find object in database!");
       throw new NotFoundException("Not exist!!!");
    }

    public List<Employee> getEmployeeByGender (Gender gender){
        log.info("Start call JPA!");
        if(!employeeRepositoryImp.findByGender(gender).isEmpty()){
            return employeeRepositoryImp.findByGender(gender);
        }
        log.error("Can't find object in database!");
        throw new NotFoundException("Not exist!!!");
    }

    public Employee updateEmployee(Employee employee) {
        log.info("Start call JPA!");
        if (employeeRepositoryImp.findById(employee.getEmployeeID()).isPresent()){
            return employeeRepositoryImp.save(employee);
        }
        log.error("Can't find object in database!");
        throw new NotFoundException("Employee NOT found!");
    }

    public void removeEmployee(Long id) {
        log.info("Start call JPA!");
        if (employeeRepositoryImp.findById(id).isPresent()){
            employeeRepositoryImp.deleteById(id);
            return;
        }
        log.error("Can't find object in database!");
        throw new NotFoundException("Employee NOT found!");
    }
}
