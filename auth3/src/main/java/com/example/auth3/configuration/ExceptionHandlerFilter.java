package com.example.auth3.configuration;

import com.example.auth3.response.LoginAndJoinResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            LoginAndJoinResponse newError = LoginAndJoinResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("expired token")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
            setErrorResponse(response, newError);
        }
            //토큰의 유효기간 만료
        catch (JwtException | IllegalArgumentException e) {
            LoginAndJoinResponse newError = LoginAndJoinResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("invalid token")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
            //유효하지 않은 토큰
            setErrorResponse(response, newError);
        }
    }
    private void setErrorResponse(
            HttpServletResponse response,
            LoginAndJoinResponse error
    ){
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(error.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try{
            response.getWriter().write(objectMapper.writeValueAsString(error));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}