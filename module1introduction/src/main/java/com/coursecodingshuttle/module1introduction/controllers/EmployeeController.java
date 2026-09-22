package com.coursecodingshuttle.module1introduction.controllers;

import com.coursecodingshuttle.module1introduction.dto.EmployeeDTO;
import com.coursecodingshuttle.module1introduction.entities.EmployeeEntity;
import com.coursecodingshuttle.module1introduction.repositories.EmployeeRepository;
import com.coursecodingshuttle.module1introduction.services.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.springframework.web.servlet.function.ServerResponse.ok;

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
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name = "employeeId") Long id){
        EmployeeDTO employeeDTO = employeeservice.getEmployeeByID(id);
        if(employeeDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDTO);
    }
    //you can change it to shorthand

    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String type){ //required = false used to make the query params optional
        return ResponseEntity.ok(employeeservice.getAllEmployees());
    }
    //now if u give age in the query parameters it doesnt work..you have to give it as inputAge

    @PostMapping
    public ResponseEntity<EmployeeDTO> postEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO employeeDTO = employeeservice.postEmployee(inputEmployee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO inputEmployee, @PathVariable(name = "employeeId") Long id){
        EmployeeDTO employeeDTO = employeeservice.getEmployeeByID(id);
        if(employeeDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeservice.updateEmployee(inputEmployee, id));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> EmployeeDelete(@PathVariable(name = "employeeId") Long id){
        EmployeeDTO employeeDTO = employeeservice.getEmployeeByID(id);
        if(employeeDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeservice.deleteEmployee(id));
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployee(@RequestBody Map<String, Object> updates,
                                             @PathVariable(name = "employeeId") Long id){
        EmployeeDTO employeeDTO = employeeservice.getEmployeeByID(id);
        if(employeeDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeservice.updatePartialEmployee(updates, id));
    }
}
