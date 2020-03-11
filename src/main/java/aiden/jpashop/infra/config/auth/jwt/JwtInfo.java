package aiden.jpashop.infra.config.auth.jwt;

import com.auth0.jwt.algorithms.Algorithm;

public class JwtInfo {

    public static final String HEADER_NAME = "Authorization";

    public static final String ISSUER = "aiden";

    public static final String TOKEN_KEY = "aidenstoken";

    public static final long EXPIRES_LIMIT = 3L;

    public static Algorithm getAlgorithm() {
        try {
            return Algorithm.HMAC256(JwtInfo.TOKEN_KEY);
        } catch (IllegalArgumentException e) {
            return Algorithm.none();
        }
    }

}
