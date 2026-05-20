package com.springBootReact.service;

import com.springBootReact.dto.EmployeeDto;
import com.springBootReact.dto.LoginDto;
import com.springBootReact.dto.RegisterDto;
import com.springBootReact.entity.Employee;
import com.springBootReact.exception.ResourceNotFoundException;
import com.springBootReact.mapper.EmployeeMapper;
import com.springBootReact.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements  EmployeeService{

    private EmployeeRepository employeeRepository;

    @Override
    public String register(RegisterDto register) {

        // Check Email Exists

        if(employeeRepository
                .findByEmail(register.getEmail())
                .isPresent()) {

            throw new RuntimeException(
                    "Email already exists");
        }

        Employee employee =
                EmployeeMapper.mapToEmployee(register);

        Employee savedEmployee =
                employeeRepository.save(employee);

        return "Registration Successful";
    }

    // LOGIN

    @Override
    public String login(LoginDto loginDto) {

        Employee employee =
                employeeRepository
                        .findByEmail(loginDto.getEmail())
                        .orElseThrow(() ->
                                new RuntimeException("Email not found"));

        if(!employee.getPassword()
                .equals(loginDto.getPassword())) {

            throw new RuntimeException(
                    "Invalid Password");
        }

        return "Login Successful";
    }
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        // Check Email Already Exists

        if(employeeRepository
                .findByEmail(employeeDto.getEmail())
                .isPresent()){

            throw new RuntimeException("Email already exists");
        }

        Employee employee =
                EmployeeMapper.mapToEmployee(employeeDto);

        Employee savedEmployee =
                employeeRepository.save(employee);

        return EmployeeMapper
                .mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(
                ()->new ResourceNotFoundException("Employee does not exist with given id :"+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);

    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees=employeeRepository.findAll();

        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updatedEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee=employeeRepository.findById(employeeId).orElseThrow(
                ()->new ResourceNotFoundException("Employee does not exist with given id :"+employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj=employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(
                ()->new ResourceNotFoundException("Employee does not exist with given id :"+employeeId));

        employeeRepository.deleteById(employeeId);
    }
}
