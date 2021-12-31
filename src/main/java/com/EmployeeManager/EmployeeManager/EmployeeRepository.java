/*
 *  Simple Repository, extends JPA Repository
 */
package com.EmployeeManager.EmployeeManager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
