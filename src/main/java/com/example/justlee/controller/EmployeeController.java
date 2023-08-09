package com.example.justlee.controller;

import com.example.justlee.entity.EmployeeEntity;
import com.example.justlee.entity.Gender;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.justlee.service.EmployeeService;
import java.util.List;

@RestController
@ApiOperation("Employee APIs")
@RequestMapping(value = "api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Operation(summary = "Get all employees", description = "Returns a list of employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The employee was not found")
    })
    @GetMapping("employees")
    public List<EmployeeEntity> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @Operation(summary = "Update employee's info by id", description = "Return employee's new info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The employee was not found")
    })
    @PutMapping("employee")
    public EmployeeEntity updateEmployee(@RequestBody EmployeeEntity employee){
        return employeeService.updateEmployee(employee);
    }
    @Operation(summary = "Get an employee by name", description = "Returns list of employees săm with input name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The employee was not found")
    })
    @GetMapping("employee/get")
    public List<EmployeeEntity> getEmployeeByName(@RequestParam String name){
        return employeeService.getEmployeesByName(name);
    }
    @Operation(summary = "Get an employee by id", description = "Returns an employee as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The employee was not found")
    })
    @GetMapping("employee/{id}")
    public EmployeeEntity getEmployeeByID(@PathVariable Long id){
        return employeeService.getEmployeesByID(id);
    }
    @Operation(summary = "Get list of employee filter by gender", description = "Returns a list of employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The employee was not found")
    })
    @GetMapping("employee/gender/get")
    public List<EmployeeEntity> getEmployeeByGender(@RequestParam Gender gender){
        return employeeService.getEmployeeByGender(gender);
    }
    @Operation(summary = "Add a new employee", description = "Returns the employee's info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The employee was not found")
    })

    @PostMapping(value = "employee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeEntity addNewEmployee(@RequestBody EmployeeEntity employee){
        return employeeService.addEmployee(employee);
    }
    @Operation(summary = "Delete an employee by id", description = "No return in response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The employee was not found")
    })
    @DeleteMapping("employee/{id}")
    public void delete(@PathVariable Long id){
        employeeService.removeEmployee(id);
    }
}
