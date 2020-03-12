package aiden.jpashop.core.member.service;

import aiden.jpashop.core.member.domain.*;
import aiden.jpashop.core.member.exception.MemberJoinException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class MemberJoinService {

    private final MemberRepository memberRepository;

    private final AuthorityRepository authorityRepository;

    @Transactional
    public Long join(Member joinRequestMember) {

        isExistUsername(joinRequestMember.getUsername());

        Member save = memberRepository.save(joinRequestMember);

        authorityRepository.save(Authority.builder()
                .member(joinRequestMember)
                .role(Role.ROLE_USER)
                .build());

        return save.getId();
    }

    private void isExistUsername(String username) {
        boolean exist = memberRepository.existsByUsername(username);

        if (exist) {
            throw new MemberJoinException("동일한 이메일 주소가 이미 존재");
        }
    }

}
