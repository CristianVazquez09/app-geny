package com.wolfpack.dto;

import com.wolfpack.model.enums.StatusAppointmentEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AppointmentDTO {

    @EqualsAndHashCode.Include
    private Integer idAppointment;

    @Positive
    private double total;

    @NotNull
    private LocalDate dateAppointment;

    @NotNull
    private StatusAppointmentEnum status;

    @NotNull
    private HourAppointmentDTO hourAppointment;

    @NotNull
    private EmployeeDTO employee;

    @NotNull
    private ClientDTO client;

    @NotNull
    private List<ServiceDTO> details;
}
