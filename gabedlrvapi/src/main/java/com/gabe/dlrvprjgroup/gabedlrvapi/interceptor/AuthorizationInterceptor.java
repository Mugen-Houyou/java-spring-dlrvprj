package com.gabe.dlrvprjgroup.gabedlrvapi.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("From Authorization Interceptor. URI: {}",  request.getRequestURI());

        // 웹, 특히 크로뮴의 경우, GET, POST 이외에도 OPTIONS을 요청해서 해당 메서드 지원 여부를 확인하는 기능이 있음.
        // 이들은 일단 통과는 시켜줘야 함.
        if(HttpMethod.OPTIONS.matches(request.getMethod())) return true;

        // .js, .png, .html 등 리소스 요청의 경우
        // 얘네도 통과는 시켜줘야 함.
        if(handler instanceof ResourceHttpRequestHandler) return true;

        // TODO: header를 검증할 것. 인증/인가 등.
        return true;
    }
}
