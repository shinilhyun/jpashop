package aiden.jpashop;

import aiden.jpashop.core.member.domain.Member;
import aiden.jpashop.infra.config.auth.UserDetailsImpl;
import aiden.jpashop.infra.config.auth.jwt.JwtInfo;
import aiden.jpashop.infra.util.DateUtils;
import aiden.jpashop.infra.util.JwtUtils;
import com.auth0.jwt.JWT;
import org.springframework.security.core.GrantedAuthority;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static String AUTH_TOKEN = generateAdminToken();
    public static String AUTH_HEADER = JwtInfo.HEADER_NAME;

    public static String generateAdminToken() {

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");

        return JWT.create()
                .withIssuer(JwtInfo.ISSUER)
                .withClaim("id", 1L)
                .withClaim("username", "aiden@hanmail.net")
                .withClaim("role", roles)
                .withExpiresAt(DateUtils.nowAfterDaysToDate(JwtInfo.EXPIRES_LIMIT))
                .sign(JwtInfo.getAlgorithm());
    }

    public static String createToken(Member member) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(member.getAuthorities());
        UserDetailsImpl userDetails = new UserDetailsImpl(member, grantedAuthorities);
        return JwtUtils.createToken(userDetails);
    }
}
