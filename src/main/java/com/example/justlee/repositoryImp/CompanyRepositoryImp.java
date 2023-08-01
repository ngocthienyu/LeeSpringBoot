package com.example.justlee.repositoryImp;

import com.example.justlee.entity.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepositoryImp extends JpaRepository<Company, Long> {
    List<Company> findAllByNameLike(String name, Pageable pageable);
}
