package com.wolfpack.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "hour_appointment")
public class HourAppointment {
    @Id
    @Column(name = "id_hour_appointment", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idHourAppointment;

    @Column(name = "hour", nullable = false)
    private LocalTime hour;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

}