package com.start.prescription.common.handler;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message;
        if(exception instanceof BadCredentialsException) {
            message = "아아디 또는 비밀번호가 맞지 않습니다.";
        } else if(exception instanceof InternalAuthenticationServiceException) {
            message = "내부적으로 발생한 시스템 문제로 인해 로그인할 수 없습니다. 시스템 관리자에게 문의하세요.";
        } else if(exception instanceof UsernameNotFoundException) {
            message = "아아디 또는 비밀번호가 맞지 않습니다.";
        } else if(exception instanceof AuthenticationCredentialsNotFoundException) {
            message = "인증 요청이 거부되었습니다. 시스템 관리자에게 문의하세요.";
        } else {
            message = "알 수 없는 이유로 로그인에 실패하였습니다. 시스템 관리자에게 문의하세요.";
        }

        request.getSession().setAttribute("message", message);
        response.sendRedirect("/login");
    }

}
