package com.harshitha.hospitalManagementSystem.entity;

import com.harshitha.hospitalManagementSystem.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroupType;

    private String gender;

    private String email;

    private LocalDate birthDate;

    @CreationTimestamp
    private LocalDate createdAt;

    @OneToOne
    @JoinColumn(name = "patient_insurance", unique = true)
    private Insurance insurance;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments = new HashSet<>();
}
