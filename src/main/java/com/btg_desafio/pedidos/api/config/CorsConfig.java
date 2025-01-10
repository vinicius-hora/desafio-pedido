package com.btg_desafio.pedidos.api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CorsConfig extends OncePerRequestFilter {
    private static final String ALLOWED_HEADERS = "Authorization, Content-Type, Content-Language";
    private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS, PATCH";
    private static final String ALLOWED_ORIGIN = "*";
    private static final Long MAX_AGE = 3600L;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletResponse res = response;
        HttpServletRequest req = request;
        res.setHeader("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
        res.setHeader("Access-Control-Allow-Methods", ALLOWED_METHODS);
        res.setHeader("Access-Control-Max-Age", String.valueOf(MAX_AGE));
        res.setHeader("Access-Control-Allow-Headers", ALLOWED_HEADERS);


        filterChain.doFilter(request, response);
    }
}
