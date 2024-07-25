package com.example.E_commerce.filter;

import com.example.E_commerce.service.JwtService;
import com.example.E_commerce.service.UserInfoDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final  String AUTHORIZATION = "authorization";
    private static final  String BEARER = "Bearer ";

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoDetailsService userInfoDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String autHeader = request.getHeader(AUTHORIZATION);
        String token = null;
        String email = null;

        if (Objects.nonNull(autHeader) && autHeader.startsWith(BEARER)){
            token = autHeader.substring(7);
            email = jwtService.extractEmail(token);

        }
        if (Objects.nonNull(email) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())){
            UserDetails userDetails =  userInfoDetailsService.loadUserByUsername(email);
            if (jwtService.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
