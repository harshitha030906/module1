package com.coursecodingshuttle.module1introduction.controllers;

import com.coursecodingshuttle.module1introduction.dto.EmployeeDTO;
import com.coursecodingshuttle.module1introduction.entities.EmployeeEntity;
import com.coursecodingshuttle.module1introduction.repositories.EmployeeRepository;
import com.coursecodingshuttle.module1introduction.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees") //parent path for all the below paths
public class EmployeeController {

    private final EmployeeService employeeservice;

    public EmployeeController(EmployeeService employeeservice){
        this.employeeservice = employeeservice;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable(name = "employeeId") Long id){
        return employeeservice.getEmployeeByID(id);
    }
    //you can change it to shorthand

    @GetMapping()
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String type){ //required = false used to make the query params optional
        return employeeservice.getAllEmployees();
    }
    //now if u give age in the query parameters it doesnt work..you have to give it as inputAge

    @PostMapping
    public EmployeeDTO postEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeservice.postEmployee(inputEmployee);
    }

    @PutMapping
    public String putEmployee(){
        return "hello from put";
    }
}
