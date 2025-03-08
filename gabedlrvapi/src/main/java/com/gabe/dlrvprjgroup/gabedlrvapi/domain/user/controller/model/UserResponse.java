package com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller.model;

import com.gabe.dlrvprjgroup.gabedlrvdb.user.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id; // DB에 저장된 인덱스 ID는 내려줘야.

    private String username;

    private String firstName;

    private String middleName;

    private String surname;

    private String email;

    private String password;

    private UserStatus status;

    private String address;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;

}
