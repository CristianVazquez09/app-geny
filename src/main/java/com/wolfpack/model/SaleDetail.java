package com.wolfpack.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sale_detail")
@IdClass(SaleDetailFK.class)
public class SaleDetail {

    @Id
    @ToString.Exclude
    private Sale sale;

    @Id
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "subtotal", nullable = false, columnDefinition = "decimal(6,2)")
    private double subtotal;

}