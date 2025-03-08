package com.gabe.dlrvprjgroup.gabedlrvdb.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {
    REGISTERED("등록됨"),
    UNREGISTERED("해지됨"),
    BANNED("밴됨"),
    ;

    private final String description;

}
