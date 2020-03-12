package aiden.jpashop.core.member.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUsername(String username);

    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findByUsername(String username);

}
