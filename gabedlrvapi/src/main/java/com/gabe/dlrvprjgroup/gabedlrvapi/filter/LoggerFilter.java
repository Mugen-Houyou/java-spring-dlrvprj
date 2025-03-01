package com.gabe.dlrvprjgroup.gabedlrvapi.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var resp = new ContentCachingResponseWrapper((HttpServletResponse) response);

        // ↑ 실행 전 ↑
        chain.doFilter(req,resp);
        // ↓ 실행 후 ↓

        // request 정보 수집
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey -> {
            var headerValue = req.getHeader(headerKey);
            headerValues
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append(", ");
        });

        var requestBody = new String(req.getContentAsByteArray());
        var requestUri = req.getRequestURI();
        var requestMethod = req.getMethod();

        log.info(">>>>> Method & URI: {} {}, header : {}, body: {}",
                requestMethod, requestUri, headerValues, requestBody);

        // response 정보 수집
        var responseHeaderValues = new StringBuilder();
        resp.getHeaderNames().forEach(headerKey -> {
            var headerValue = resp.getHeader(headerKey);
            headerValues
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append(", ");

        });
        var responseBody = new String(resp.getContentAsByteArray());

        log.info("<<<<< Method & URI: {} {}, header : {}, body: {}", requestMethod,requestUri, responseHeaderValues, responseBody);

        // 이거 반드시 있어야!
        // 없으면 클라이언트에게 response body가 안 감.
        resp.copyBodyToResponse();

    }

//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
}





















