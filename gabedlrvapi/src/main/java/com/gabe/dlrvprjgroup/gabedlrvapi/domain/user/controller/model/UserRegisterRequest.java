package com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    @NotBlank
    @Email
    private String email;

    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String address;

    @NotBlank
    private String firstName;

    private String middleName;

    private String surname;

}
