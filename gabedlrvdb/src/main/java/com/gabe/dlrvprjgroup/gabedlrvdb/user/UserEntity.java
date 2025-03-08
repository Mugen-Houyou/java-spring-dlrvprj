package com.gabe.dlrvprjgroup.gabedlrvdb.user;


import com.gabe.dlrvprjgroup.gabedlrvdb.BaseEntity;
import com.gabe.dlrvprjgroup.gabedlrvdb.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name="dlrvuser")
@Data
@EqualsAndHashCode(callSuper = true) // 데이터를 상속받는 클래스가 있기 때문에
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserEntity extends BaseEntity {

    @Column(length = 50, nullable = true)
    private String username;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = true)
    private String middleName;

    @Column(length = 50, nullable = true)
    private String surname;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(length = 150, nullable = false)
    private String address;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;

}

















