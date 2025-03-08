package com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.service;
// User 도메인의 로직을 처리하는 서비스.

import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCode;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.exception.CommonApiException;
import com.gabe.dlrvprjgroup.gabedlrvdb.user.UserEntity;
import com.gabe.dlrvprjgroup.gabedlrvdb.user.UserRepository;
import com.gabe.dlrvprjgroup.gabedlrvdb.user.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity register(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(it -> {
                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());
                    return userRepository.save(userEntity);
                }).orElseThrow(()-> new CommonApiException(ErrorCode.NULL_POINT,"UserEntity가 NULL."));
    }

    public UserEntity getUserWithThrow (
            String email,
            String password
    ){
        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                email,
                password,
                UserStatus.REGISTERED
        ).orElseThrow(()-> new CommonApiException(ErrorCode.USER_NOT_VALID,"유효한 유저가 아님."));
    }

    public UserEntity getUserWithThrow (
            Long userId
    ){
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(
                userId,
                UserStatus.REGISTERED
        ).orElseThrow(()-> new CommonApiException(ErrorCode.USER_NOT_VALID,"유효한 유저가 아님."));
    }

    public UserEntity signIn(
            String email,
            String password
    ){
        return getUserWithThrow(email,password);
    }

}



















