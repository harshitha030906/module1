package com.coursecodingshuttle.module1introduction.services;

import com.coursecodingshuttle.module1introduction.dto.EmployeeDTO;
import com.coursecodingshuttle.module1introduction.entities.EmployeeEntity;
import com.coursecodingshuttle.module1introduction.exceptions.ResourcenotFoundException;
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

    public boolean isExists(Long id){
        return employeeRepository.existsById(id);
    }

    public EmployeeDTO getEmployeeByID(Long id){
        if(isExists(id)){
            return modelMapper.map(employeeRepository.findById(id), EmployeeDTO.class);
        }
        throw new ResourcenotFoundException("employee not found");
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
        if(isExists(id)){
            employeeRepository.deleteById(id);
            return true;
        }
        throw new ResourcenotFoundException("employee not found");
    }

    public EmployeeDTO updatePartialEmployee(Map<String,Object> updates, Long id){
        if(isExists(id)){
            EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
            updates.forEach((field, value) -> {
                Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
            });
            EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);
            return modelMapper.map(updatedEmployee, EmployeeDTO.class);
        }
        throw new ResourcenotFoundException("employee not found");
    }
}
