package com.wolfpack.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClientRegistrationDTO {

    @EqualsAndHashCode.Include
    private Integer idClient;

    @NotNull
    private UserRegistrationDTO user;
}