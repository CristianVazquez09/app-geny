package com.wolfpack.repo;

import com.wolfpack.model.Employee;
import com.wolfpack.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IEmployeeRepo extends IGenericRepo<Employee, Integer>{

    @Query("SELECT e.user FROM Employee e WHERE e.idEmployee = :employeeId")
    User findUserByEmployeeId(@Param("employeeId") Integer employeeId) ;



}
