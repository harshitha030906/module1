package com.harshitha.hospitalManagementSystem.repository;

import com.harshitha.hospitalManagementSystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}