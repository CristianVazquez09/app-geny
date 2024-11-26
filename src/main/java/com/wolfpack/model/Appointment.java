package com.wolfpack.model;

import com.wolfpack.model.enums.StatusAppointmentEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment", nullable = false)
    @EqualsAndHashCode.Include
    private Integer idAppointment;

    @Column(name = "total", nullable = false, columnDefinition = "decimal(6,2)")
    private double total;

    @Column(name = "date_appointment", nullable = false)
    private LocalDate dateAppointment;

    @Column(name = "status", nullable = false, columnDefinition = "ENUM('PENDIENTE', 'CONFIRMADA', 'COMPLETADA','CANCELADA')")
    @Enumerated(EnumType.STRING)
    private StatusAppointmentEnum status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_hour_appointment", nullable = false)
    private HourAppointment hourAppointment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "appointment_detail",
            joinColumns = @JoinColumn(name = "id_appointment", referencedColumnName = "id_appointment"),
            inverseJoinColumns = @JoinColumn(name = "id_service", referencedColumnName = "id_service")
    )
    private List<Service> details;

}