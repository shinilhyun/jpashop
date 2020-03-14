package aiden.jpashop.core.member.service;

import aiden.jpashop.core.member.domain.Member;
import aiden.jpashop.core.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class MemberFindService {

    private final MemberRepository memberRepository;

    /**
     * Member 정보 가져오기
     */
    public Member getMemberDetail(Long memberId) {

        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("no such Member"));
    }

    public Page<Member> getAllMemberPage(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

}
