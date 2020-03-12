package aiden.jpashop.infra.util;

import aiden.jpashop.infra.config.auth.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class PrincipalUtils {

    public static UserDetailsImpl getPrincipal() {
        return (UserDetailsImpl)SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public static Long getId() {
        return getPrincipal().getId();
    }

    public static String getUsername() {
        return getPrincipal().getUsername();
    }

}
