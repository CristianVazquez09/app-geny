package com.wolfpack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserRegistrationDTO {

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

    @NotBlank
    @Size(min = 1, max = 60)
    private String password;

    @NotNull
    private boolean enabled;
}

