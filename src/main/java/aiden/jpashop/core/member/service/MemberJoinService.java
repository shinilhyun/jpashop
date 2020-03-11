package aiden.jpashop.core.member.service;

import aiden.jpashop.core.member.domain.Member;
import aiden.jpashop.core.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class MemberJoinService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member joinRequestMember) {

        isExistUsername(joinRequestMember.getUsername());

        Member save = memberRepository.save(joinRequestMember);

        return save.getId();
    }

    private void isExistUsername(String username) {
        boolean exist = memberRepository.existsByUsername(username);

        if (exist) {
            throw new IllegalArgumentException("동일한 이메일 주소가 이미 존재");
        }
    }

}
