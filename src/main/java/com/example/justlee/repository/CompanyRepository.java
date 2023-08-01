package com.example.justlee.repository;

import com.example.justlee.entity.Company;
import com.example.justlee.entity.Company_;
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
    public List<Company> getCompanyByName(String name){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Company> query = builder.createQuery(Company.class);
        Root<Company> root = query.from(Company.class);
        Predicate conditionLIKE = builder.like(root.get(Company_.NAME), name);
        Predicate conditionAND = builder.equal(root.get(Company_.COMPANY_ID), 1);
        Predicate condition = builder.and(conditionAND, conditionLIKE);
        query.select(root).where(condition);
        return em.createQuery(query).getResultList();
    }
}
