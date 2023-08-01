package com.example.justlee.service;

import com.example.justlee.repository.CompanyRepository;
import com.example.justlee.repositoryImp.CompanyRepositoryImp;
import com.example.justlee.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepositoryImp companyRepositoryImp;
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies(Integer pageNo, Integer pageSize) {
        return companyRepositoryImp.findAll(PageRequest.of(pageNo, pageSize, Sort.by("name"))).getContent();
    }

    public List<Company> getAllCompaniesByName(Integer pageNo, Integer pageSize, String name) {
        if(Objects.isNull(pageNo) || Objects.isNull(pageSize)){
            return companyRepository.getCompanyByName(name);
        }
        return companyRepositoryImp.findAllByNameLike(name, PageRequest.of(pageNo, pageSize, Sort.by("name")));
    }
}
