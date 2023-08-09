package com.example.justlee;

import com.example.justlee.repositoryImp.EmployeeRepositoryImp;
import com.example.justlee.entity.EmployeeEntity;
import com.example.justlee.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
public class TodoServiceTest {
    @MockBean
    EmployeeService service;

    @MockBean
    EmployeeRepositoryImp repository;


    @Test
    public void TestNotNull() {
        Assert.assertEquals(null, service.addEmployee(new EmployeeEntity()));
        Assert.assertEquals(repository.findAll().size(), 0);
        Assert.assertEquals(new BigDecimal(3),  new BigDecimal(3).multiply(new BigDecimal(1)));
    }

}
