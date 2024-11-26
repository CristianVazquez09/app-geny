package com.wolfpack.dto;


import com.wolfpack.model.enums.ProductTypeEnum;
import jakarta.validation.constraints.*;
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
public class ProductDTO {

    @EqualsAndHashCode.Include
    private Integer idProduct;

    @NotNull @NotEmpty
    @Size(min = 3, max = 45)
    private String name;

    @NotNull
    @Positive
    @Digits(integer = 6, fraction = 2)
    private double price;

    @NotNull
    @Positive
    @Min(value = 1)
    private int quantityAvailable;

    @NotNull
    private ProductTypeEnum type;




}
