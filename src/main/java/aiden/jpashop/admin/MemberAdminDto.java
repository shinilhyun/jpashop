package aiden.jpashop.admin;

import aiden.jpashop.core.member.domain.Member;
import aiden.jpashop.core.support.Address;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
public class MemberAdminDto {

    @Data
    @NoArgsConstructor
    public static class Info {

        private String username;
        private String tel;
        private Address address;

        @Builder
        public Info(String username, String password, String tel, Address address) {
            this.username = username;
            this.tel = tel;
            this.address = address;
        }

        public static Info of(Member member) {
            return Info.builder()
                    .username(member.getUsername())
                    .address(member.getAddress())
                    .tel(member.getTel())
                    .build();
        }

        public static Page<Info> of(Page<Member> members) {
            return members.map(Info::of);
        }
    }
}
