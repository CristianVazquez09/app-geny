package com.wolfpack.service.impl;

import com.wolfpack.model.Employee;
import com.wolfpack.model.User;
import com.wolfpack.model.enums.RoleEnum;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.IEmployeeRepo;
import com.wolfpack.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmployeeImpl extends CRUDServiceImpl<Employee,Integer> implements IEmployeeService{


    private final IEmployeeRepo repo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleEnum ROLE_EMPLOYEE = RoleEnum.EMPLOYEE;
    @Override
    protected IGenericRepo<Employee, Integer> getRepo() {
        return repo;
    }

    @Override
    public Employee saveWithEncryptedPassword(Employee employee) throws Exception {
        String passwordEncode = passwordEncoder.encode(employee.getUser().getPassword());
        employee.getUser().setRole(ROLE_EMPLOYEE);
        employee.getUser().setPassword(passwordEncode);

        return repo.save(employee);
    }

    @Override
    public Employee updateWithExistingPassword(Employee employee, Integer id) throws Exception {

        User userFind = repo.findUserByEmployeeId(id);
        employee.getUser().setIdUser(userFind.getIdUser());
        employee.getUser().setPassword(userFind.getPassword());
        employee.getUser().setRole(ROLE_EMPLOYEE);
        return repo.save(employee);
    }
}

