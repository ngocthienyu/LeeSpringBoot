package com.example.justlee.repositoryImp;

import com.example.justlee.entity.CompanyEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepositoryImp extends JpaRepository<CompanyEntity, Long> {
    List<CompanyEntity> findAllByNameLike(String name, Pageable pageable);
}
