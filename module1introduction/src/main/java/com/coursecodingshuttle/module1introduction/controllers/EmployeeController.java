package com.coursecodingshuttle.module1introduction.controllers;

import com.coursecodingshuttle.module1introduction.dto.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employees") //parent path for all the below paths
public class EmployeeController {
    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable(name = "employeeId") Long id){
        return new EmployeeDTO(id, "Harshitha", "Balabhadruni", "harshitha@gmail.com", 25000, 20);
    }
    //you can change it to shorthand

    @GetMapping()
    public String getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                  @RequestParam(required = false) String type){ //required = false used to make the query params optional
        return "Hi age : " + age + " " + type;
    }
    //now if u give age in the query parameters it doesnt work..you have to give it as inputAge

    @PostMapping
    public EmployeeDTO postEmployee(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setEmployeeID(100L);
        return inputEmployee;
    }

    @PutMapping
    public String putEmployee(){
        return "hello from put";
    }
}
