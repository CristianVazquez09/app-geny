package com.wolfpack.model;

import com.wolfpack.model.enums.StatusEmployeeEnum;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee", nullable = false)
    @EqualsAndHashCode.Include
    private Integer idEmployee;

    @Column(name = "address", nullable = false, length = 120)
    private String address;

    @Column(name = "status", columnDefinition = "ENUM('INABILITY', 'VACATION', 'ACTIVE')")
    @Enumerated(EnumType.STRING)
    private StatusEmployeeEnum status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_user_data", referencedColumnName = "id_user_data")
    private User user;


}