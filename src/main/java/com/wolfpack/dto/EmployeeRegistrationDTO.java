package com.wolfpack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wolfpack.model.enums.StatusEmployeeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmployeeRegistrationDTO {

    @EqualsAndHashCode.Include
    private Integer idEmployee;

    @NotNull @NotBlank
    @Size(min = 1, max = 120)
    private String address;

    @NotNull
    private StatusEmployeeEnum status;

    @NotNull
    private UserRegistrationDTO user;
}
