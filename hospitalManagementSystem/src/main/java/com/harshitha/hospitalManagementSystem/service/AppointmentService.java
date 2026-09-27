package com.harshitha.hospitalManagementSystem.service;

import com.harshitha.hospitalManagementSystem.entity.Appointment;
import com.harshitha.hospitalManagementSystem.entity.Doctor;
import com.harshitha.hospitalManagementSystem.entity.Patient;
import com.harshitha.hospitalManagementSystem.repository.AppointmentRepository;
import com.harshitha.hospitalManagementSystem.repository.DoctorRepository;
import com.harshitha.hospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment createAppointment(Appointment appointment, Long patientId, Long doctorId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        appointmentRepository.save(appointment);

        return appointment;
    }
}
