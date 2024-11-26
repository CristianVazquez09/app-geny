package com.wolfpack.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service", nullable = false)
    @EqualsAndHashCode.Include
    private Integer idService;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "duration", nullable = false)
    private LocalTime duration;

    @Column(name = "price", nullable = false,columnDefinition = "decimal(6,2)")
    private double price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_image", referencedColumnName = "id_image")
    private Image image;

    @OneToMany(mappedBy = "service", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<ServiceProduct> products;
}