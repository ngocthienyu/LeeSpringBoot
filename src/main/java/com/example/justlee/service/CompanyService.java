package com.example.justlee.service;

import com.example.justlee.repository.CompanyRepository;
import com.example.justlee.repositoryImp.CompanyRepositoryImp;
import com.example.justlee.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {
    private final int PAGE_NO = 0;
    private final int PAGE_SIZE = 5;
    @Autowired
    private CompanyRepositoryImp companyRepositoryImp;
    @Autowired
    private CompanyRepository companyRepository;

    public Page<Company> getAllCompanies(Integer pageNo, Integer pageSize) {
        return companyRepositoryImp.findAll(PageRequest.of(pageNo, pageSize, Sort.by("name")));
    }

    public Page<Company> getAllCompaniesByName(Integer pageNo, Integer pageSize, String name) {
        Pageable pageRequest = (Objects.isNull(pageNo) || Objects.isNull(pageSize))
                ? PageRequest.of(PAGE_NO, PAGE_SIZE, Sort.by("name"))
                : PageRequest.of(pageNo, pageSize,  Sort.by("name"));
        List<Company> listCompany = companyRepositoryImp.findAllByNameLike(name, pageRequest);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), listCompany.size());

        List<Company> pageContent = listCompany.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, listCompany.size());
    }
}
