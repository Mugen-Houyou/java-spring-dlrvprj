package com.gabe.dlrvprjgroup.gabedlrvapi.config.web;

import com.gabe.dlrvprjgroup.gabedlrvapi.interceptor.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
//import org.delivery.api.interceptor.AuthorizationInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthorizationInterceptor authorizationInterceptor;

    private final List<String> DEFAULT_ALLOWEDS = List.of(
            "/",
            "favicon.ico",
            "/error"
    );
    private final List<String> OPEN_APIS = List.of(
            "/open-api/**"
    );
    private final List<String> SWAGGER_URIS = List.of(
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**"
    );


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(authorizationInterceptor)
                .excludePathPatterns(OPEN_APIS)
                .excludePathPatterns(DEFAULT_ALLOWEDS)
                .excludePathPatterns(SWAGGER_URIS)
                ;
    }

}
