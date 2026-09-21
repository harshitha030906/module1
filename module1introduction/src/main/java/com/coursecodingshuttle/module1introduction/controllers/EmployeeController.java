package com.coursecodingshuttle.module1introduction.controllers;

import com.coursecodingshuttle.module1introduction.dto.EmployeeDTO;
import com.coursecodingshuttle.module1introduction.entities.EmployeeEntity;
import com.coursecodingshuttle.module1introduction.repositories.EmployeeRepository;
import com.coursecodingshuttle.module1introduction.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employees") //parent path for all the below paths
public class EmployeeController {

    private final EmployeeService employeeservice;
    private final ModelMapper modelMapper;

    public EmployeeController(EmployeeService employeeservice, ModelMapper modelMapper){
        this.employeeservice = employeeservice;
        this.modelMapper = modelMapper;
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

    @PutMapping("/{employeeId}")
    public EmployeeDTO updateEmployee(EmployeeDTO inputEmployee, @PathVariable(name = "employeeId") Long id){
        return employeeservice.updateEmployee(inputEmployee, id);
    }

    @DeleteMapping("/{employeeId}")
    public boolean EmployeeDelete(@PathVariable(name = "employeeId") Long id){
        return employeeservice.deleteEmployee(id);
    }

    @PatchMapping("/{employeeId}")
    public EmployeeDTO updatePartialEmployee(@RequestBody Map<String, Object> updates,
                                             @PathVariable(name = "employeeId") Long id){
        return employeeservice.updatePartialEmployee(updates, id);
    }
}
