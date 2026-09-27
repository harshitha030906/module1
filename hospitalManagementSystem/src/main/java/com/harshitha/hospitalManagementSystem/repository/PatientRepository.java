package com.harshitha.hospitalManagementSystem.repository;

import com.harshitha.hospitalManagementSystem.dto.CPatientInfo;
import com.harshitha.hospitalManagementSystem.dto.iPatientInfo;
import com.harshitha.hospitalManagementSystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Query("select p.id as id, p.name as name, p.email as email from Patient p")
    List<iPatientInfo> getAllPatients();

    @Query("select new com.harshitha.hospitalManagementSystem.dto.CPatientInfo(p.id, p.name) " +
    "from Patient p")
    List<CPatientInfo> getAllCPatients();
}
