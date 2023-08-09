package com.example.justlee.service;

import com.example.justlee.entity.CompanyEntity;
import com.example.justlee.repository.CompanyRepository;
import com.example.justlee.repositoryImp.CompanyRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

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

    public Page<CompanyEntity> getAllCompanies(Integer pageNo, Integer pageSize) {
        return companyRepositoryImp.findAll(PageRequest.of(pageNo, pageSize, Sort.by("name")));
    }

    public Page<CompanyEntity> getAllCompaniesByName(Integer pageNo, Integer pageSize, String name) {
        Pageable pageRequest = (Objects.isNull(pageNo) || Objects.isNull(pageSize))
                ? PageRequest.of(PAGE_NO, PAGE_SIZE, Sort.by("name"))
                : PageRequest.of(pageNo, pageSize,  Sort.by("name"));
        List<CompanyEntity> listCompanyEntity = companyRepositoryImp.findAllByNameLike(name, pageRequest);
        return new PageImpl<>(listCompanyEntity, pageRequest, listCompanyEntity.size());
    }
}
