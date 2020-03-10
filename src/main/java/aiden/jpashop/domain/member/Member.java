package aiden.jpashop.domain.member;

import aiden.jpashop.domain.base.Address;
import aiden.jpashop.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String tel;

    @Embedded
    private Address address;

    @Builder
    public Member(String username, String password, String name, String tel, Address address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.tel = tel;
           this.address = address;
    }

    public void updateMemberInfo(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }
}
