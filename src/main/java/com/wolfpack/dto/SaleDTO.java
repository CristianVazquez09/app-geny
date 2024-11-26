package com.wolfpack.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wolfpack.model.enums.PaymentMethodEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SaleDTO {

    @EqualsAndHashCode.Include
    private Integer idSale;

    @NotNull
    private LocalDate date;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 8, fraction = 2)
    private double total;

    @NotNull
    private PaymentMethodEnum paymentMethod;

    @NotNull
    private ClientDTO client;

    @NotNull
    private EmployeeDTO employee;

    @NotNull
    @JsonManagedReference
    private List<SaleDetailDTO> saleDetails;
}
