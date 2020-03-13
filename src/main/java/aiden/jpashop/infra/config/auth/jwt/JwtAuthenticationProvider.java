package aiden.jpashop.infra.config.auth.jwt;

import aiden.jpashop.infra.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) {

        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("Bad token");
        }

        String token = authentication.getCredentials().toString();

        if (JwtUtils.verify(token)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(token);
            return new JwtAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Bad token");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
