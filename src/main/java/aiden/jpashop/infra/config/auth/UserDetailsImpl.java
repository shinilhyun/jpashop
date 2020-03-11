package aiden.jpashop.infra.config.auth;

import aiden.jpashop.core.member.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserDetailsImpl extends User {

    private Long id;

    public UserDetailsImpl(Long id, String username, List<GrantedAuthority> authorities) {
        super(username, "", authorities);
        this.id = id;
    }

    public UserDetailsImpl(Member member, List<GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.id = member.getId();
    }

}
