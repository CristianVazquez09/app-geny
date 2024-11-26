package com.wolfpack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wolfpack.model.enums.RoleEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDTO {

    @EqualsAndHashCode.Include
    private Integer idUser;

    @NotBlank
    @Size(min = 1, max = 40)
    private String name;

    @NotBlank
    @Size(min = 1, max = 45)
    @Pattern(regexp = "^\\+?[0-9\\s]*$", message = "Phone number must contain only digits and optional spaces.")
    private String phone;

    @NotBlank
    @Email
    @Size(min = 1, max = 45)
    private String email;

    @NotNull
    private boolean enabled;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RoleEnum role;
}

