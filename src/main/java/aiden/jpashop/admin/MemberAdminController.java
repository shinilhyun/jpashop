package aiden.jpashop.admin;

import aiden.jpashop.core.member.domain.Member;
import aiden.jpashop.core.member.service.MemberFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberAdminController {

    private final MemberFindService memberFindService;

    @GetMapping("/api/v1/admin/members")
    public ResponseEntity getAllMember(Pageable pageable) {

        Page<Member> memberPage = memberFindService.getAllMemberPage(pageable);

        return ResponseEntity.ok().body(MemberAdminDto.Info.of(memberPage));
    }
}
