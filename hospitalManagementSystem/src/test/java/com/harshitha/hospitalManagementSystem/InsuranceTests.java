package com.harshitha.hospitalManagementSystem;

import com.harshitha.hospitalManagementSystem.entity.Appointment;
import com.harshitha.hospitalManagementSystem.entity.Insurance;
import com.harshitha.hospitalManagementSystem.repository.InsuranceRepository;
import com.harshitha.hospitalManagementSystem.repository.PatientRepository;
import com.harshitha.hospitalManagementSystem.service.AppointmentService;
import com.harshitha.hospitalManagementSystem.service.InsuranceService;
import com.harshitha.hospitalManagementSystem.service.PatientService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTests  {

    @Autowired
    InsuranceService insuranceService;

    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentService appointmentService;

    @Test
    public void insuranceTest(){
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_321")
                .createdAt(LocalDate.now())
                .validUntil(LocalDate.of(2030, 1, 1))
                .provider("HDFC")
                .build();

        var updatedInsurance = insuranceService.assignInsuranceToPatient(2L,insurance);
        System.out.println(updatedInsurance);
    }

    @Test
    public void deletePatientsTest(){
        patientService.deletePatient(2L);
    }

    @Test
    public void createAppointmentTest(){

        Appointment appointment = Appointment.builder().appointmentTime(LocalDateTime.now()).reason("cancer").build();
        Appointment updatedAppointment = appointmentService.createAppointment(appointment, 1L, 2L);

        System.out.println(updatedAppointment);

        patientService.deletePatient(1L);
    }
}
