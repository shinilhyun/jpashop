package aiden.jpashop.core.member.service;

import aiden.jpashop.core.member.domain.Member;
import aiden.jpashop.core.member.domain.MemberRepository;
import aiden.jpashop.core.support.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJoinServiceTest {

    @Autowired
    MemberJoinService memberJoinService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EntityManager em;

    @Test
    public void 회원가입테스트() {

        String username = "aidenshin@hanmail.net";

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode("1234"))
                .tel("01012344556")
                .address(new Address("seoul", "ddddddd", "07283"))
                .build();

        //When
        Long result = memberJoinService.join(member);

        em.flush();
        em.clear();

        //Then
        Member saveMember = memberRepository.findByUsername(username).get();
        assertEquals(result, saveMember.getId());


    }

}