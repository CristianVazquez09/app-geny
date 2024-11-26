package com.wolfpack.model;

import com.wolfpack.model.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user_data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_data", nullable = false)
    @EqualsAndHashCode.Include
    private Integer idUser;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "enabled", nullable = false )
    private boolean enabled;

    @Enumerated(EnumType.STRING)  // Indica que el ENUM se guardará como String en la BD
    @Column(columnDefinition = "ENUM('ADMIN', 'CLIENT', 'EMPLOYEE')", nullable = false)
    private RoleEnum role;

}