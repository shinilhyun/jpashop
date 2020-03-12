package aiden.jpashop.api;

import aiden.jpashop.core.member.domain.Authority;
import aiden.jpashop.core.support.Address;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class MemberDto {

    private Long id;
    private String username;
    private String password;
    private String tel;
    private Address address;
    private List<Authority> authorities = new ArrayList<>();

    @Data
    public static class JoinRequest {

        @Email
        @NotNull
        private String username;
        @NotNull
        private String password;
        @NotNull
        private String tel;
        private Address address;

        @Builder
        public JoinRequest(@Email String username, @NotNull String password, @NotNull String tel, Address address) {
            this.username = username;
            this.password = password;
            this.tel = tel;
            this.address = address;
        }

    }
}
