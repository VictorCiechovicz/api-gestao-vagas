package com.gestaovagas.security;


import com.gestaovagas.provider.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        String header = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/company")) {
            if (header != null) {
                var sub = jwtProvider.validateToken(header);

                if (sub.isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                request.setAttribute("company_id", sub);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(sub, null, Collections.emptyList());

                SecurityContextHolder.getContext().setAuthentication(auth);

            }
        }


        filterChain.doFilter(request, response);
    }
}
