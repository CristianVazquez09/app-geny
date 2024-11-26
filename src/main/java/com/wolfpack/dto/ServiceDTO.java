package com.wolfpack.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServiceDTO {

    @EqualsAndHashCode.Include
    private Integer idService;

    @NotNull @NotEmpty
    @Size(min = 3, max = 45)
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime duration;

    @NotNull
    @Positive
    @Digits(integer = 6, fraction = 2)
    private double price;

    @JsonManagedReference
    @NotNull
    private List<ServiceProductDTO> products;
}
