package aiden.jpashop.infra.config.auth;

import aiden.securitysample.auth.jwt.JwtInfo;
import aiden.securitysample.util.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class BaseSecurityHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {


        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setHeader(JwtInfo.HEADER_NAME, JwtUtil.createToken(userDetails));

    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
    }
}
