package com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.converter;

import com.gabe.dlrvprjgroup.gabedlrvapi.common.annotation.Converter;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCode;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.exception.CommonApiException;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller.model.UserRegisterRequest;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller.model.UserResponse;
import com.gabe.dlrvprjgroup.gabedlrvdb.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest registerRequest){

        return Optional.ofNullable(registerRequest)
                .map( it -> {
                    // entity로.
                    return  UserEntity.builder()
                            .username(registerRequest.getUsername())
                            .email(registerRequest.getEmail())
                            .password(registerRequest.getPassword())
                            .address(registerRequest.getAddress())
                            .firstName(registerRequest.getFirstName())
                            .middleName(registerRequest.getMiddleName())
                            .surname(registerRequest.getSurname())
                            .build();
                }).orElseThrow(() -> new CommonApiException(ErrorCode.NULL_POINT,"UserRegisterRequest의 registerRequest 중 NULL값 존재."));
    }

    public UserResponse toResponse(UserEntity newlyCreatedEntity) {

        return Optional.ofNullable(newlyCreatedEntity)
                .map(it -> {
                    return UserResponse.builder()
                            .id(newlyCreatedEntity.getId())
                            .username(newlyCreatedEntity.getUsername())
                            .status(newlyCreatedEntity.getStatus())
                            .email(newlyCreatedEntity.getEmail())
                            .address(newlyCreatedEntity.getAddress())
                            .firstName(newlyCreatedEntity.getFirstName())
                            .middleName(newlyCreatedEntity.getMiddleName())
                            .surname(newlyCreatedEntity.getSurname())
                            .registeredAt(newlyCreatedEntity.getRegisteredAt())
                            .unregisteredAt(newlyCreatedEntity.getUnregisteredAt())
                            .lastLoginAt(newlyCreatedEntity.getLastLoginAt())
                            .build();
                }).orElseThrow(()-> new CommonApiException(ErrorCode.NULL_POINT, "UserEntity가 NULL."));
    }
}


















