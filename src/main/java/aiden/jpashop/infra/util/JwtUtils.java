package aiden.jpashop.infra.util;

import aiden.jpashop.infra.config.auth.UserDetailsImpl;
import aiden.jpashop.infra.config.auth.jwt.JwtInfo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtUtils {

    public static String createToken(UserDetailsImpl userDetails) {
        return createToken(userDetails, DateUtils.nowAfterDaysToDate(JwtInfo.EXPIRES_LIMIT));
    }

    private static String createToken(UserDetailsImpl userDetails, Date date) {
        try {

            List<String> roles = new ArrayList<>();

            userDetails.getAuthorities().forEach(a -> {
                roles.add(a.getAuthority());
            });

            return JWT.create()
                    .withIssuer(JwtInfo.ISSUER)
                    .withClaim("id", userDetails.getId())
                    .withClaim("username", userDetails.getUsername())
                    .withClaim("role", roles)
                    .withExpiresAt(date)
                    .sign(JwtInfo.getAlgorithm());
        } catch (JWTCreationException createEx) {
            return null;
        }
    }

    public static Boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(JwtInfo.getAlgorithm()).build();
            verifier.verify(token);

            return Boolean.TRUE;
        } catch (JWTVerificationException verifyEx) {
            return Boolean.FALSE;
        }
    }

    public static String refreshToken(UserDetailsImpl userDetails) {
        return createToken(userDetails, DateUtils.nowAfterDaysToDate(JwtInfo.EXPIRES_LIMIT));
    }

    public static DecodedJWT tokenToJwt(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException decodeEx) {
            return null;
        }
    }
}