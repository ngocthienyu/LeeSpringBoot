package com.example.justlee.entity;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, String> hometown;
	public static volatile SingularAttribute<Employee, Gender> gender;
	public static volatile SingularAttribute<Employee, LocalDate> dob;
	public static volatile SingularAttribute<Employee, Long> employeeID;
	public static volatile SingularAttribute<Employee, Company> company;
	public static volatile SingularAttribute<Employee, String> fullname;

	public static final String HOMETOWN = "hometown";
	public static final String GENDER = "gender";
	public static final String DOB = "dob";
	public static final String EMPLOYEE_ID = "employeeID";
	public static final String COMPANY = "company";
	public static final String FULLNAME = "fullname";

}

