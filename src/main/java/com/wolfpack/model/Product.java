package com.wolfpack.model;

import com.wolfpack.model.enums.ProductTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@ToString
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", nullable = false)
    @EqualsAndHashCode.Include
    private Integer idProduct;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "price", nullable = false,columnDefinition = "decimal(6,2)")
    private double price;

    @Column(name = "quantity_available", nullable = false)
    private int quantityAvailable;

    @Column(name= "product_type",columnDefinition = "ENUM('SERVICIO', 'VENTA')")
    @Enumerated(EnumType.STRING)
    private ProductTypeEnum type;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_image", referencedColumnName = "id_image")
    private Image image;

}