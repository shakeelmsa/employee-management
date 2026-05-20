package com.springBootReact.service;

import com.springBootReact.dto.EmployeeDto;
import com.springBootReact.dto.LoginDto;
import com.springBootReact.dto.RegisterDto;

import java.util.List;

public interface EmployeeService {

    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updatedEmployee(Long employeeId, EmployeeDto updatedEmployee);

    void deleteEmployee(Long employeeId);
}
