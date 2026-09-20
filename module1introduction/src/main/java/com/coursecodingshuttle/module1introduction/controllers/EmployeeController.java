package com.coursecodingshuttle.module1introduction.controllers;

import com.coursecodingshuttle.module1introduction.dto.EmployeeDTO;
import com.coursecodingshuttle.module1introduction.entities.EmployeeEntity;
import com.coursecodingshuttle.module1introduction.repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees") //parent path for all the below paths
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployee(@PathVariable(name = "employeeId") Long id){
        return employeeRepository.findById(id).orElse(null);
    }
    //you can change it to shorthand

    @GetMapping()
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String type){ //required = false used to make the query params optional
        return employeeRepository.findAll();
    }
    //now if u give age in the query parameters it doesnt work..you have to give it as inputAge

    @PostMapping
    public EmployeeEntity postEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }

    @PutMapping
    public String putEmployee(){
        return "hello from put";
    }
}
