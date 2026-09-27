package com.harshitha.hospitalManagementSystem.service;

import com.harshitha.hospitalManagementSystem.entity.Insurance;
import com.harshitha.hospitalManagementSystem.entity.Patient;
import com.harshitha.hospitalManagementSystem.repository.InsuranceRepository;
import com.harshitha.hospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Insurance assignInsuranceToPatient(Long patientId, Insurance insurance){
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(insurance);

        insurance.setPatient(patient);//optional - to maintain bidirectional flow

        return insurance;
    }
}
