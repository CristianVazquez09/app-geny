package com.wolfpack.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HourAppointmentDTO {

    @EqualsAndHashCode.Include
    private Integer idHour;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime hour;

    @NotNull
    private boolean enabled;
}
