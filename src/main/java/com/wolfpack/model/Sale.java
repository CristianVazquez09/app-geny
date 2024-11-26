package com.wolfpack.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wolfpack.model.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale", nullable = false)
    @EqualsAndHashCode.Include
    private Integer idSale;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "total", nullable = false,columnDefinition = "decimal(8,2)")
    private double total;

    @Enumerated(EnumType.STRING)  // Indica que el ENUM se guardará como String en la BD
    @Column(columnDefinition = "ENUM('CREDITO', 'TRANSFERENCIA', 'EFECTIVO')", nullable = false)
    private PaymentMethodEnum paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;


    @OneToMany(mappedBy = "sale", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<SaleDetail> saleDetails;

}