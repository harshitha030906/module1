package com.coursecodingshuttle.module1introduction.services;

import com.coursecodingshuttle.module1introduction.dto.EmployeeDTO;
import com.coursecodingshuttle.module1introduction.entities.EmployeeEntity;
import com.coursecodingshuttle.module1introduction.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeByID(Long id){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().
                map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO postEmployee(EmployeeDTO inputEmployee){
        EmployeeEntity saveEmployeeEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity employeeEntity = employeeRepository.save(saveEmployeeEntity);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployee(EmployeeDTO inputEmployee, Long id){
        EmployeeEntity employeeEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        employeeEntity.setEmployeeID(id);
        EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);
        return modelMapper.map(updatedEmployee, EmployeeDTO.class);
    }

    public boolean deleteEmployee(Long id){
        boolean exists = employeeRepository.existsById(id);
        if(!exists){
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO updatePartialEmployee(Map<String,Object> updates, Long id){
        boolean exists = employeeRepository.existsById(id);
        if(!exists){
            return null;
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);
        return modelMapper.map(updatedEmployee, EmployeeDTO.class);
    }

}
