package aiden.jpashop.api;

import aiden.jpashop.core.member.domain.Authority;
import aiden.jpashop.core.member.domain.Member;
import aiden.jpashop.core.support.Address;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String username;
    private String password;
    private String tel;
    private Address address;
    private List<Authority> authorities = new ArrayList<>();

    @Data
    @NoArgsConstructor
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

    @Data
    @NoArgsConstructor
    public static class Detail {

        private String username;
        private String tel;
        private Address address;

        @Builder
        public Detail(String username, String password, String tel, Address address) {
            this.username = username;
            this.tel = tel;
            this.address = address;
        }

        public static Detail of(Member member) {
            return Detail.builder()
                    .username(member.getUsername())
                    .address(member.getAddress())
                    .tel(member.getTel())
                    .build();
        }

        public static Page<Detail> of(Page<Member> members) {
            return members.map(Detail::of);
        }
    }
}
