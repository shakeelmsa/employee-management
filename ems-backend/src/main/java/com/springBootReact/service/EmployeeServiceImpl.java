package com.springBootReact.service;

import com.springBootReact.Utils.JwtUtil;
import com.springBootReact.dto.EmployeeDto;
import com.springBootReact.dto.LoginDto;
import com.springBootReact.dto.RegisterDto;
import com.springBootReact.entity.Employee;
import com.springBootReact.exception.ResourceNotFoundException;
import com.springBootReact.mapper.EmployeeMapper;
import com.springBootReact.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.springBootReact.exception.EmailAlreadyExistsException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements  EmployeeService{

    private EmployeeRepository employeeRepository;

    @Override
    public String register(RegisterDto register) {

        // Check Email Exists
        System.out.println("1");
        if(employeeRepository
                .findByEmail(register.getEmail())
                .isPresent()) {

            System.out.println("2");

            throw new EmailAlreadyExistsException(
                    "Email already exists");
        }

        System.out.println("3");

        Employee employee =
                EmployeeMapper.mapToEmployee(register);

        System.out.println("4");

        Employee savedEmployee =
                employeeRepository.save(employee);

        System.out.println("5");

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

        return JwtUtil.generateToken(
                employee.getEmail());

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
