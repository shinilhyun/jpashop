package aiden.jpashop.core.member.domain;

import aiden.jpashop.core.support.Address;
import aiden.jpashop.core.support.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String tel;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Authority> authorities = new ArrayList<>();

    @Builder
    public Member(String username, String password, String name, String tel, Address address) {
        this.username = username;
        this.password = password;
        this.tel = tel;
           this.address = address;
    }

    public void updateMemberInfo(String name, String tel) {
        this.tel = tel;
    }
}
