package com.example.justlee;

import com.example.justlee.controller.EmployeeController;
import com.example.justlee.entity.CompanyEntity;
import com.example.justlee.entity.EmployeeEntity;
import com.example.justlee.entity.EmployeeDTO;
import com.example.justlee.entity.Gender;
import com.example.justlee.service.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeTest {
    @MockBean
    EmployeeService employeeService;
    @Autowired
    private MockMvc mvc;

    @Before
    public void setup(){
        CompanyEntity companyEntityA = new CompanyEntity(1L, "SaolaSoft", "72 Tran Dang Ninh", 1975);
        CompanyEntity companyEntityB = new CompanyEntity(2L, "BitScreener Soft", "1 Pham Van bach", 2001);
        CompanyEntity companyEntityC = new CompanyEntity(3L, "Tell Me Why", "37 Cau Giay", 1886);

        List<EmployeeEntity> employees = Arrays.asList(
                new EmployeeEntity(1L, "Hari Won", Gender.Male, "Singapore", LocalDate.now(), companyEntityA),
                new EmployeeEntity(2L, "Hari Billiard", Gender.Other, "HongKong", LocalDate.now(), companyEntityB),
                new EmployeeEntity(3L, "Kalte", Gender.Female, "Thailands", LocalDate.now(), companyEntityA));
        Mockito.when(employeeService.getEmployeesByName(anyString())).thenReturn(employees);
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);
        Mockito.when(employeeService.getEmployeesByID(anyLong())).thenReturn(employees.stream().findFirst().get());
    }

    @Test
    public void whenValidId_thenReturn(){
        String name = "Lee";
        List<EmployeeEntity> employees = employeeService.getEmployeesByName(name);
        Assert.assertTrue(!employees.stream()
                .filter(emp -> emp.getFullname().contains(name))
                .findFirst()
                .isPresent());
    }

    @Test
    public void testAPIStatus_isOK1() throws Exception {
        var request = MockMvcRequestBuilders.get("/api/employees").contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void testAPIStatus_isOK2() throws Exception {
        var request = MockMvcRequestBuilders
                .get("/api/employee/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn();
    }

    @Test
    public void testAPIStatus_isOK3() throws Exception {
        var request = MockMvcRequestBuilders
                .get("/api/employee/get")
                .param("name", "Hari")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testAPIStatus_isOK4() throws Exception {
        var request = MockMvcRequestBuilders
                .get("/api/employee/{id}", 10)
                .contentType(MediaType.APPLICATION_JSON);
                    mvc.perform(request)
                            .andDo(MockMvcResultHandlers.print())
                            .andExpect(status().isNotFound());
    }

    @Test
    public void testModelMapper(){
        final ModelMapper mapper = new ModelMapper();
        CompanyEntity companyEntityA = new CompanyEntity(1L,
                "SaolaSoft",
                "72 Tran Dang Ninh",
                1975);
        EmployeeEntity employee =  new EmployeeEntity(1L,
                "Hari Won",
                Gender.Male,
                "Singapore",
                LocalDate.of(1998, Month.APRIL, 15),
                companyEntityA);
        EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
        System.out.println(employeeDTO);
        System.out.println(employee);
        Assert.assertEquals(true, employeeDTO.getAge() == employee.getAge());
    }



}
