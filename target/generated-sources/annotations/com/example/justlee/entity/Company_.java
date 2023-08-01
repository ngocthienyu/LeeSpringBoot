package com.example.justlee.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Company.class)
public abstract class Company_ {

	public static volatile SingularAttribute<Company, Long> companyID;
	public static volatile SingularAttribute<Company, String> address;
	public static volatile SingularAttribute<Company, String> name;
	public static volatile ListAttribute<Company, Employee> employees;
	public static volatile SingularAttribute<Company, Integer> since;

	public static final String COMPANY_ID = "companyID";
	public static final String ADDRESS = "address";
	public static final String NAME = "name";
	public static final String EMPLOYEES = "employees";
	public static final String SINCE = "since";

}

