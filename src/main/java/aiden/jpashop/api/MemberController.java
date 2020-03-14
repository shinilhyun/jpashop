package aiden.jpashop.api;

import aiden.jpashop.core.member.domain.Member;
import aiden.jpashop.core.member.service.MemberFindService;
import aiden.jpashop.core.member.service.MemberJoinService;
import aiden.jpashop.infra.util.PrincipalUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJoinService memberJoinService;

    private final MemberFindService memberFindService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public ResponseEntity joinMember(@RequestBody @Valid MemberDto.JoinRequest joinRequest) {

        Member joinMember = Member.builder()
                .username(joinRequest.getUsername())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .tel(joinRequest.getTel())
                .address(joinRequest.getAddress())
                .build();

        Long memberId = memberJoinService.join(joinMember);

        return ResponseEntity.ok(memberId);
    }

    @GetMapping("/api/v1/member")
    public ResponseEntity getMemberDetail() {

        Long memberId = PrincipalUtils.getId();
        Member member = memberFindService.getMemberDetail(memberId);

        return ResponseEntity.ok(MemberDto.Detail.of(member));
    }

    @GetMapping("/api/v1/members")
    public ResponseEntity getAllMemberPage(Pageable pageable) {

        Page<Member> memberPage = memberFindService.getAllMemberPage(pageable);

        return ResponseEntity.ok().body(MemberDto.Detail.of(memberPage));
    }
}
