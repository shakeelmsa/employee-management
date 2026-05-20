package com.springBootReact.mapper;

import com.springBootReact.dto.EmployeeDto;
import com.springBootReact.dto.RegisterDto;
import com.springBootReact.entity.Employee;

public class EmployeeMapper {

    // Entity -> DTO

    public static EmployeeDto mapToEmployeeDto(
            Employee employee){

        return new EmployeeDto(

                employee.getId(),

                employee.getFirstName(),

                employee.getLastName(),

                employee.getEmail(),

                employee.getPhoneNumber(),

                employee.getPassword()
        );
    }

    // EmployeeDto -> Entity

    public static Employee mapToEmployee(
            EmployeeDto employeeDto){

        return new Employee(

                employeeDto.getId(),

                employeeDto.getFirstName(),

                employeeDto.getLastName(),

                employeeDto.getEmail(),

                employeeDto.getPhoneNumber(),

                employeeDto.getPassword()
        );
    }

    // RegisterDto -> Entity

    public static Employee mapToEmployee(
            RegisterDto registerDto){

        return new Employee(

                null,

                registerDto.getFirstName(),

                registerDto.getLastName(),

                registerDto.getEmail(),

                registerDto.getPhoneNumber(),

                registerDto.getPassword()
        );
    }
}