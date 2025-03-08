package com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.business;

import com.gabe.dlrvprjgroup.gabedlrvapi.common.annotation.Business;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCode;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.exception.CommonApiException;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.business.TokenBusiness;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.controller.model.TokenResponse;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller.model.UserRegisterRequest;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller.model.UserResponse;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller.model.UserSignInRequest;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.converter.UserConverter;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    /**
     * 신규 회원 가입 처리 로직
         * 해당 request를 entity로, 이후 DB에 저장.
     *
     * @param requestBody
     * @return
     */
    public UserResponse register(@Valid UserRegisterRequest requestBody) {
//        var convertedEntity = userConverter.toEntity(requestBody);
//        var newlyCreatedEntity = userService.register(convertedEntity);
//        var response = userConverter.toResponse(newlyCreatedEntity);
//        return response;
        // Java 8부터는 함수형으로 아래와 같이 작성 가능.
        return Optional.ofNullable(requestBody)
                .map(userConverter::toEntity) // JSON 포맷으로 받은 요청을 Entity로 변환.
                .map(userService::register) // 변환된 Entity를 들고 서비스 레이어로 넘어가서 실제 DB에 반영.
                .map(userConverter::toResponse) // 실제 DB에 반영된 뒤 받은 리턴값을 Response 형태로 변환. 이후 리턴.
                .orElseThrow(() -> new CommonApiException(ErrorCode.NULL_POINT, "UserRegisterRequest에 NULL."));

    }

    /**
     * 로그인 로직
     * 유저로부터 email과 password를 받아,
     * UserEntity의 존재 유무 체크,
     * 있다면 토큰을 response로 내려줌
     *
     * @param requestBody
     * @return // (토큰 리스폰스)
     */
    public TokenResponse signIn(@Valid UserSignInRequest requestBody) {
        var userEntity = userService.signIn(
                requestBody.getEmail(),
                requestBody.getPassword()
        );

        // 유효하지 않을 경우 throw. (별도 코드 작성은 필요하지 않음)

        // 유효할 경우 토큰 발급하여 유저에게 반환.
        return tokenBusiness.issueToken(userEntity);
    }

    public UserResponse me(Long userId) {
        var userEntity = userService.getUserWithThrow(userId);
        var resp = userConverter.toResponse(userEntity);
        return resp;
    }
}






























