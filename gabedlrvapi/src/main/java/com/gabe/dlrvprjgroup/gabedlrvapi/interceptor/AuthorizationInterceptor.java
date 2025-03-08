package com.gabe.dlrvprjgroup.gabedlrvapi.interceptor;

import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCode;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.TokenErrorCode;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.exception.CommonApiException;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.business.TokenBusiness;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("From Authorization Interceptor. URI: {}",  request.getRequestURI());

        // 웹, 특히 크로뮴의 경우, GET, POST 이외에도 OPTIONS을 요청해서 해당 메서드 지원 여부를 확인하는 기능이 있음.
        // 이들은 일단 통과는 시켜줘야 함.
        if(HttpMethod.OPTIONS.matches(request.getMethod())) return true;

        // .js, .png, .html 등 리소스 요청의 경우
        // 얘네도 통과는 시켜줘야 함.
        if(handler instanceof ResourceHttpRequestHandler) return true;

        // 인증/인가 토큰 헤더 검증.
        var accessToken = request.getHeader("authorization-token");
        if (accessToken==null)
            throw new CommonApiException(TokenErrorCode.TOKEN_MISSING);

        // 헤더로부터 받은 토큰의 유저 검증.
        var userId = tokenBusiness.validateAccessToken(accessToken);
        if (userId != null) {
            // 로컬 스레드로, 즉 한 가지 리퀘스트에 대해서만 유효하게 글로벌하게 저장할 수 있는 영역.
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST); // 단일 리퀘스트만.
            return true;
        }

        throw new CommonApiException(ErrorCode.BAD_REQUEST);
    }
}
