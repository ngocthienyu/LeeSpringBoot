package com.example.justlee.controller;

import com.example.justlee.entity.CompanyEntity;
import com.example.justlee.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiOperation("Company APIs")
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("companies")
    public Page<CompanyEntity> getAllCompany(@RequestParam Integer pageNo,
                                             @RequestParam Integer pageSize) {
        return companyService.getAllCompanies(pageNo, pageSize);
    }

    @GetMapping("companies/name")
    public Page<CompanyEntity> getAllCompanyByName(@RequestParam(required = false) Integer pageNo,
                                                   @RequestParam(required = false) Integer pageSize,
                                                   @RequestParam String name) {
        return companyService.getAllCompaniesByName(pageNo, pageSize, name);
    }
}
