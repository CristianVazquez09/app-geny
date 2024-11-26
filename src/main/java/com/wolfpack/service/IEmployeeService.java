package com.wolfpack.service;

import com.wolfpack.model.Employee;

public interface IEmployeeService extends ICRUDService<Employee, Integer> {

    Employee saveWithEncryptedPassword(Employee employee) throws Exception;

    Employee updateWithExistingPassword(Employee employee, Integer id) throws Exception;
}
