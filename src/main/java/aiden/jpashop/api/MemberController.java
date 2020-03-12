package aiden.jpashop.api;

import aiden.jpashop.core.member.domain.Member;
import aiden.jpashop.core.member.service.MemberJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJoinService memberJoinService;

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
}
