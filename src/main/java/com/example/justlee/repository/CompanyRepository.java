package com.example.justlee.repository;

import com.example.justlee.entity.CompanyEntity;
import com.example.justlee.entity.CompanyEntity_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CompanyRepository {
    @PersistenceContext
    private EntityManager em;
    public List<CompanyEntity> getCompanyByName(String name){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CompanyEntity> query = builder.createQuery(CompanyEntity.class);
        Root<CompanyEntity> root = query.from(CompanyEntity.class);
        Predicate conditionLIKE = builder.like(root.get(CompanyEntity_.NAME), name);
        Predicate conditionAND = builder.equal(root.get(CompanyEntity_.COMPANY_ID), 1);
        Predicate condition = builder.and(conditionAND, conditionLIKE);
        query.select(root).where(condition);
        return em.createQuery(query).getResultList();
    }
}
