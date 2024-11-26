package com.wolfpack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "service_product")
@IdClass(ServiceProductFK.class)
public class ServiceProduct {

    @Id
    private Service service;

    @Id
    private Product product;

    @Column(name = "quantity_product", nullable = false)
    private Integer quantityProduct;

}