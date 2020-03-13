package aiden.jpashop.infra.config.auth.jwt;

import aiden.jpashop.infra.config.auth.UserDetailsImpl;
import aiden.jpashop.infra.util.JwtUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

        DecodedJWT decodedJWT = JwtUtils.tokenToJwt(token);

        if (decodedJWT == null) {
            throw new BadCredentialsException("Not used Token");
        }

        Long id = decodedJWT.getClaim("id").asLong();
        String username = decodedJWT.getClaim("username").asString();
        String[] roles = decodedJWT.getClaim("role").asArray(String.class);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(roles);

        return new UserDetailsImpl(id, username, grantedAuthorities);
    }
}
