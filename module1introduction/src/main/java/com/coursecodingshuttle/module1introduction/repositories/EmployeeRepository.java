package com.coursecodingshuttle.module1introduction.repositories;

import com.coursecodingshuttle.module1introduction.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
