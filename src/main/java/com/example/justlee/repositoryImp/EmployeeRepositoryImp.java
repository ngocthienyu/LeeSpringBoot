package com.example.justlee.repositoryImp;

import com.example.justlee.entity.EmployeeEntity;
import com.example.justlee.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EmployeeRepositoryImp extends JpaRepository<EmployeeEntity, Long> {
    @Query(value = "SELECT * FROM employees WHERE fullname LIKE %" + ":name" + "%",
           nativeQuery = true)
    List<EmployeeEntity> findByFullname(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM employees WHERE gender = :#{#gender?.toString()}",
            nativeQuery = true)
    List<EmployeeEntity> findByGender(@Param("gender") Gender gender);
}
