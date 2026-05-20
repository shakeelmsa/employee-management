package com.springBootReact.controller;

import com.springBootReact.dto.LoginDto;
import com.springBootReact.dto.RegisterDto;
import com.springBootReact.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterDto registerDto){

        String response =
                employeeService.register(registerDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // LOGIN

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginDto loginDto){

        String response =
                employeeService.login(loginDto);

        return ResponseEntity.ok(response);
    }
}
