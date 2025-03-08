package com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserSignInRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;


}
