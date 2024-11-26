package com.wolfpack.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServiceProductDTO {

    @JsonBackReference
    @EqualsAndHashCode.Include
    @NotNull
    private ServiceDTO service;

    @EqualsAndHashCode.Include
    @NotNull
    private ProductDTO product;

    @NotNull @NotEmpty
    @Positive
    private Integer quantityProduct;

}
