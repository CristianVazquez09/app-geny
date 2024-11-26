package com.wolfpack.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class ServiceProductFK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_service", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;
}
